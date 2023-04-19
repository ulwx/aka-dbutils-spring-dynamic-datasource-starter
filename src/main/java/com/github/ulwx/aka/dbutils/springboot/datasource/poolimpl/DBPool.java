package com.github.ulwx.aka.dbutils.springboot.datasource.poolimpl;

import com.github.ulwx.aka.dbutils.spring.multids.DSPoolType;
import com.github.ulwx.aka.dbutils.spring.multids.DataSourceInfo;
import com.github.ulwx.aka.dbutils.springboot.datasource.*;
import com.github.ulwx.aka.dbutils.springboot.datasource.poolimpl.DBPoolAttr.TYPE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public abstract class DBPool {
    private static final Logger log = LoggerFactory.getLogger(DruidDBPoolImpl.class);

   public  abstract void  close(DataSourceInfo dataSource) throws Exception ;

   public abstract void init(DataSourceInfo dataSource)throws  Exception;

    public abstract DataSource configNewDataSource(DBPoolAttr dbPoolAttr);
    public abstract String getType();

    public abstract void configProperties(DataSource p, PoolConfig poolConfig, Map<String, Method> getMethods);

    final static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private   final ConcurrentHashMap<String, IDBPoolAttrSourceConfigurer> ConfigurerPool = new ConcurrentHashMap<>();

    public DBPoolAttrSource invokeRefClassHandler(String dsName,String refClassName) throws Exception{
        if(ConfigurerPool.get(dsName)==null){
            synchronized (this){
                if(ConfigurerPool.get(dsName)==null){
                    IDBPoolAttrSourceConfigurer configurer=
                            (IDBPoolAttrSourceConfigurer) Class.forName(refClassName).
                                    getConstructor().newInstance();
                    ConfigurerPool.put(dsName,configurer);
                }
            }
        }
        IDBPoolAttrSourceConfigurer configurer=ConfigurerPool.get(dsName);
        DBPoolAttrSource dbPoolAttrSource=new  DBPoolAttrSource();
        configurer.configProperties(dsName,dbPoolAttrSource);
        return dbPoolAttrSource;
    }
    public  void refClassHandler(DBPoolAttr dbPoolAttr) throws Exception{
        if(dbPoolAttr.getType()!=null && dbPoolAttr.getType().trim().equalsIgnoreCase(TYPE.custom)){
            String refClassName=dbPoolAttr.getRefClass();
            try{
                DBPoolAttrSource dbPoolAttrSource=invokeRefClassHandler(dbPoolAttr.getDsName(),refClassName);
                if(dbPoolAttr.getUrl()!=null){
                    log.warn("本地配置的"+dbPoolAttr.getUrl()+"会被忽略！");
                }
                dbPoolAttr.setUrl(dbPoolAttrSource.getUrl());
                if(dbPoolAttr.getUsername()!=null){
                    log.warn("本地配置的"+dbPoolAttr.getUsername()+"会被忽略！");
                }
                dbPoolAttr.setUsername(dbPoolAttrSource.getUsername());
                if(dbPoolAttr.getPassword()!=null){
                    log.warn("本地配置的"+dbPoolAttr.getPassword()+"会被忽略！");
                }
                dbPoolAttr.setPassword(dbPoolAttrSource.getPassword());
                if(dbPoolAttr.getDriverClassName()!=null){
                    log.warn("本地配置的"+dbPoolAttr.getDriverClassName()+"会被忽略！");
                }
                dbPoolAttr.setDriverClassName(dbPoolAttrSource.getDriverClassName());


            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }

    }
    public DataSourceInfo getNewDataSource(DBPoolAttr dbPoolAttr) throws Exception {

        DataSource p=null;
        p=configNewDataSource(dbPoolAttr);
        PoolConfig poolConfig=dbPoolAttr.getAttributes();
        Map<String, Method> getMethods = DsConfigUtil.getGetterMethods(poolConfig.getClass());
        getMethods.remove("poolType");
        getMethods.remove("enable");
        configProperties(p,poolConfig,getMethods);
        DataSource finalDataSource=p;
        boolean isShardingJDBCDataSource=false;
        if(!this.getType().equalsIgnoreCase(DSPoolType.ShardingJDBC)) { //不为shardingjdbc数据源时
            Map<String, Method> setMethods = DsConfigUtil.getSetterMethods(p.getClass());
            log.debug("type:" + this.getType());
            for (String name : getMethods.keySet()) {
                Object value = getMethods.get(name).invoke(poolConfig);
                if (log.isDebugEnabled()) {
                    log.debug("--name:" + name + ",value:" + value + "");
                }
                if (value != null) {
                    if (log.isDebugEnabled()) {
                        log.debug("++name:" + name + ",value:" + value + "");
                    }
                    setMethods.get(name).invoke(p, value);
                }

            }

            if (dbPoolAttr.getSeata() != null) {
                if (dbPoolAttr.getSeata().trim().equalsIgnoreCase("AT")) {
                    finalDataSource = (DataSource) Class.forName("io.seata.rm.datasource.DataSourceProxy").
                            getConstructor(DataSource.class).newInstance(p);
                } else if (dbPoolAttr.getSeata().equalsIgnoreCase("XA")) {
                    finalDataSource = (DataSource) Class.forName("io.seata.rm.datasource.xa.DataSourceProxyXA").
                            getConstructor(DataSource.class).newInstance(p);
                }

            }

        }else{
            isShardingJDBCDataSource=true;
        }

        DataSourceInfo dataSourceInfo =  new DataSourceInfo(dbPoolAttr.getDsName(), finalDataSource);
        dataSourceInfo.setSeata(dbPoolAttr.getSeata());
        dataSourceInfo.setOriginalDataSource(p);
        init(dataSourceInfo);

        return dataSourceInfo;
    }

}
