package com.github.ulwx.aka.dbutils.springboot.datasource;

import com.github.ulwx.aka.dbutils.spring.multids.DataSourceInfo;
import com.github.ulwx.aka.dbutils.springboot.datasource.poolimpl.DBPool;
import com.github.ulwx.aka.dbutils.springboot.datasource.poolimpl.DBPoolAttr;
import com.github.ulwx.aka.dbutils.springboot.datasource.poolimpl.DBPoolAttr.TYPE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AkaDataSourceFactory {
    private static final Logger log = LoggerFactory.getLogger(AkaDataSourceFactory.class);
    public static AkaDataSourceFactory instance = new AkaDataSourceFactory();

    private final   ConcurrentHashMap<String, DataSourceInfo> dataSourceMap = new ConcurrentHashMap<>();


    public AkaDataSourceFactory(){
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
               for(String key:dataSourceMap.keySet()){
                   try {
                       PoolFactory.getDBPool(dataSourceMap.get(key).getPoolType()).close(dataSourceMap.get(key));
                   }catch (Exception e){
                       log.error(""+e,e);
                   }
               }
            }
        }));
    }

    public ConcurrentHashMap<String, DataSourceInfo> getDataSourceMap() {
        return dataSourceMap;
    }



    public static AkaDataSourceFactory getInstance() {
        return instance;
    }

    private PoolConfig  getFinalPoolConfig(DsTypeConfig dsType,String dsName,
                                           DataSourceConfig dataSourceConfig) throws Exception{
        dataSourceConfig.setDsName(dsName);
        PoolConfig src = null;
        PoolConfig des = null;
        if (dataSourceConfig.getDruid() != null) {
            src = dsType.getDruid();
            des = dataSourceConfig.getDruid();
        } else if (dataSourceConfig.getHikari() != null) {
            src = dsType.getHikari();
            des = dataSourceConfig.getHikari();
        } else if (dataSourceConfig.getDbcp2() != null) {
            src = dsType.getDbcp2();
            des = dataSourceConfig.getDbcp2();
        }else if (dataSourceConfig.getTomcatdbpool() != null) {
            src = dsType.getTomcatdbpool();
            des = dataSourceConfig.getTomcatdbpool();
        }else if( dataSourceConfig.getShardingJdbc()!=null){
            src = dsType.getShardingJdbc();
            des = dataSourceConfig.getShardingJdbc();
        }
        if (des == null) {
            int count = 0;
            if (dsType.getDruid() != null) {
                count++;
                src = dsType.getDruid();
            }
            if (dsType.getHikari() != null) {
                count++;
                src = dsType.getHikari();
            }
            if (dsType.getDbcp2() != null) {
                count++;
                src = dsType.getDbcp2();
            }
            if (dsType.getTomcatdbpool() != null) {
                count++;
                src = dsType.getTomcatdbpool();
            }
            if (dsType.getShardingJdbc()!= null) {
                count++;
                src = dsType.getShardingJdbc();
            }
            if (count > 1) {
                throw new RuntimeException("构造失败！spring.datasource.aka-dynamic.datasources."
                        + dsName + "不能确定数据源类型，请通过spring.datasource.aka-dynamic.datasources." +
                        dsName + ".<druid|hikari|dbcp2>.enable:true指定！");
            } else if (count <= 0) {
                throw new RuntimeException("构造失败！spring.datasource.aka-dynamic.ds-type下没有配置属于源类型！");
            } else {
                des = src;
                src = null;

            }
        }
        PoolConfig finalpoolConfig = merge(src, des);

        return finalpoolConfig;
    }
    public void build(DsTypeConfig dsType, Map<String, DataSourceConfig> datasources) throws Exception {
        for (String dsName : datasources.keySet()) {
            DataSourceConfig dataSourceConfig = datasources.get(dsName);
            if(!dataSourceConfig.getEnable()){
                continue;
            }
            PoolConfig finalpoolConfig=getFinalPoolConfig(dsType,dsName,dataSourceConfig);

            DBPool dbPool = PoolFactory.getDBPool(finalpoolConfig.getPoolType());
            DBPoolAttr dbPoolAttr = new DBPoolAttr();
            dbPoolAttr.setEnable(dataSourceConfig.getEnable());
            dbPoolAttr.setUrl(dataSourceConfig.getUrl());
            dbPoolAttr.setUsername(dataSourceConfig.getUsername());
            dbPoolAttr.setPassword(dataSourceConfig.getPassword());
            dbPoolAttr.setDriverClassName(dataSourceConfig.getDriverClassName());
            dbPoolAttr.setDsName(dataSourceConfig.getDsName());
            dbPoolAttr.setSeata(dataSourceConfig.getSeata());
            dbPoolAttr.setType(dataSourceConfig.getType());
            dbPoolAttr.setRefClass(dataSourceConfig.getRefClass());
            dbPoolAttr.setCheckTime(dataSourceConfig.getCheckTime());
            dbPoolAttr.setInitCheckTime(dataSourceConfig.getInitCheckTime());
            dbPoolAttr.setAttributes(finalpoolConfig);
            //处理refClass
            dbPool.refClassHandler(dbPoolAttr);
            //生成最终DataSource

            DataSourceInfo dataSourceInfo = dbPool.getNewDataSource(dbPoolAttr);

            dataSourceMap.put(dsName, dataSourceInfo);

            //检测远程dataSource
            if(dbPoolAttr.getType()!=null
                    && dbPoolAttr.getType().trim().equalsIgnoreCase(TYPE.custom)) {

                int checkTime = 0;
                int initDelay=  0;
                try {
                    checkTime = Integer.valueOf(dbPoolAttr.getCheckTime());
                    initDelay =Integer.valueOf(dbPoolAttr.getInitCheckTime());
                } catch (Exception ex) {

                }
                if (checkTime > 0) {
                    if(initDelay==0){
                        initDelay=checkTime;
                    }

                    executorService.scheduleWithFixedDelay(() -> {

                        try {
                            DBPoolAttrSource dbPoolAttrSourceNew =
                                    dbPool.invokeRefClassHandler(dbPoolAttr.getDsName(),dataSourceConfig.getRefClass());
                            DBPoolAttrSource old = new DBPoolAttrSource();
                            old.setUsername(dbPoolAttr.getUsername());
                            old.setUrl(dbPoolAttr.getUrl());
                            old.setDriverClassName(dbPoolAttr.getDriverClassName());
                            old.setPassword(dbPoolAttr.getPassword());
                            boolean ret = dbPoolAttrSourceNew.equals(old);
                            if (!ret) {
                                DataSourceInfo dataSourceInfoOld = dataSourceMap.get(dsName);
                                PoolFactory.getDBPool(dataSourceInfoOld.getPoolType()).close(dataSourceInfoOld);

                                dbPoolAttr.setUrl(dbPoolAttrSourceNew.getUrl());
                                dbPoolAttr.setUsername(dbPoolAttrSourceNew.getUsername());
                                dbPoolAttr.setPassword(dbPoolAttrSourceNew.getPassword());
                                dbPoolAttr.setDriverClassName(dbPoolAttrSourceNew.getDriverClassName());

                                DataSourceInfo dataSourceInfoNew = dbPool.getNewDataSource(dbPoolAttr);
                                dataSourceMap.put(dsName, dataSourceInfoNew);
                            }

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }, initDelay, checkTime, TimeUnit.SECONDS);
                }
            }
        }

    }

    final static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
    public static PoolConfig merge(PoolConfig srcPoolConfig, PoolConfig desPoolConfig) throws Exception {

        PoolConfig finalConfig = desPoolConfig.getClass().getConstructor().newInstance();
        Map<String, Method> setmap = DsConfigUtil.getSetterMethods(desPoolConfig.getClass());
        Map<String, Method> getmap = DsConfigUtil.getGetterMethods(desPoolConfig.getClass());
        for (String name : getmap.keySet()) {
            Method getmethod = getmap.get(name);
            if(srcPoolConfig!=null) {
                Object srcValue = getmethod.invoke(srcPoolConfig);
                if (srcValue != null) {
                    if(setmap.get(name)!=null) {
                        log.debug(name+":"+srcValue);
                        setmap.get(name).invoke(finalConfig, srcValue);
                    }
                }
            }
            Object desValue=getmethod.invoke(desPoolConfig);
            if(desValue!=null){
                if(setmap.get(name)!=null) {
                    setmap.get(name).invoke(finalConfig, desValue);
                }
            }

        }

        return finalConfig;
    }
}
