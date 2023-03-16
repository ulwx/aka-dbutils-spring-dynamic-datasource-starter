package com.github.ulwx.aka.springboot.datasource;

import com.github.ulwx.aka.springboot.datasource.poolimpl.DBPool;
import com.github.ulwx.aka.springboot.datasource.poolimpl.DBPoolAttr;
import com.github.ulwx.aka.springboot.datasource.poolimpl.PoolType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AkaDataSourceFactory {
    private static final Logger log = LoggerFactory.getLogger(AkaDataSourceFactory.class);
    public static AkaDataSourceFactory instance = new AkaDataSourceFactory();

    private Map<String, DataSourceWapper> dataSourceMap = new HashMap<>();


    public AkaDataSourceFactory(){
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
               for(String key:dataSourceMap.keySet()){
                   try {
                       dataSourceMap.get(key).getDbPool().close(dataSourceMap.get(key));
                   }catch (Exception e){
                       log.error(""+e,e);
                   }
               }
            }
        }));
    }

    public Map<String, DataSourceWapper> getDataSourceMap() {
        return dataSourceMap;
    }

    public void setDataSourceMap(Map<String, DataSourceWapper> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
    }

    public static AkaDataSourceFactory getInstance() {
        return instance;
    }

    public void build(DsTypeConfig dsType, Map<String, DataSourceConfig> datasources) throws Exception {
        for (String dsName : datasources.keySet()) {
            DataSourceConfig dataSourceConfig = datasources.get(dsName);
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

            DBPool dbPool = PoolType.getDBPool(finalpoolConfig.getPoolType());
            DBPoolAttr dbPoolAttr = new DBPoolAttr();
            dbPoolAttr.setUrl(dataSourceConfig.getUrl());
            dbPoolAttr.setUsername(dataSourceConfig.getUsername());
            dbPoolAttr.setPassword(dataSourceConfig.getPassword());
            dbPoolAttr.setDriverClassName(dataSourceConfig.getDriverClassName());
            dbPoolAttr.setDsName(dataSourceConfig.getDsName());
            dbPoolAttr.setSeata(dataSourceConfig.getSeata());
            dbPoolAttr.setAttributes(finalpoolConfig);
            DataSourceWapper dataSourceWapper = dbPool.getNewDataSource(dbPoolAttr);
            dataSourceMap.put(dsName, dataSourceWapper);
        }

    }


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
