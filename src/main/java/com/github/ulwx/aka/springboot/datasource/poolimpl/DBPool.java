package com.github.ulwx.aka.springboot.datasource.poolimpl;

import com.github.ulwx.aka.springboot.datasource.DataSourceWapper;
import com.github.ulwx.aka.springboot.datasource.DsConfigUtil;
import com.github.ulwx.aka.springboot.datasource.PoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class DBPool {
    private static final Logger log = LoggerFactory.getLogger(DruidDBPoolImpl.class);

   public  abstract void  close(DataSourceWapper dataSource) throws Exception ;

   public abstract void init(DataSourceWapper dataSource)throws  Exception;

    public abstract DataSource configNewDataSource(DBPoolAttr dbPoolAttr);
    public abstract String getType();

    public abstract void configProperties(DataSource p, PoolConfig poolConfig, Map<String, Method> getMethods);


    public DataSourceWapper getNewDataSource(DBPoolAttr dbPoolAttr) throws Exception {

        DataSource p=configNewDataSource(dbPoolAttr);

        PoolConfig poolConfig=dbPoolAttr.getAttributes();
        Map<String, Method> getMethods = DsConfigUtil.getGetterMethods(poolConfig.getClass());
        getMethods.remove("poolType");
        getMethods.remove("enable");
        configProperties(p,poolConfig,getMethods);

        Map<String, Method> setMethods = DsConfigUtil.getSetterMethods(p.getClass());
        log.debug("type:"+this.getType());
        for (String name : getMethods.keySet()) {
            Object value = getMethods.get(name).invoke(poolConfig);
            log.debug("--name:" + name + ",value:" + value + "");
            if (value != null) {
                log.debug("++name:" + name + ",value:" + value + "");
                setMethods.get(name).invoke(p, value);
            }

        }

        DataSourceWapper dataSourceWapper=null;
        DataSource finalDataSource=p;
        if(dbPoolAttr.getSeata()!=null ){
            if(!this.getType().equals(PoolType.ShardingJDBC)) {
                if (dbPoolAttr.getSeata().equalsIgnoreCase("AT")) {
                    finalDataSource = (DataSource) Class.forName("io.seata.rm.datasource.DataSourceProxy").
                            getConstructor(DataSource.class).newInstance(p);
                } else if (dbPoolAttr.getSeata().equalsIgnoreCase("XA")) {
                    finalDataSource = (DataSource) Class.forName("io.seata.rm.datasource.xa.DataSourceProxyXA").
                            getConstructor(DataSource.class).newInstance(p);
                }
            } else{
                //sharding jdbc单独处理
            }
        }else{

        }
        dataSourceWapper = new DataSourceWapper(dbPoolAttr.getDsName(), finalDataSource);
        dataSourceWapper.setPoolType(this.getType());
        dataSourceWapper.setSeata(dbPoolAttr.getSeata());
        dataSourceWapper.setOriginalDataSource(p);
        dataSourceWapper.setDbPool(this);
        init(dataSourceWapper);

        return dataSourceWapper;
    }

}
