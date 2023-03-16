package com.github.ulwx.aka.springboot.datasource.poolimpl;

import com.github.ulwx.aka.springboot.datasource.DataSourceWapper;
import com.github.ulwx.aka.springboot.datasource.PoolConfig;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Map;

public class TomcatDBPoolImpl extends DBPool {
    public static TomcatDBPoolImpl instance=new TomcatDBPoolImpl();


    @Override
    public String getType() {
        return PoolType.TOMCAT_DB_POOL;
    }

    @Override
    public DataSource configNewDataSource(DBPoolAttr dbPoolAttr){
        PoolProperties p = new PoolProperties();
        p.setUsername(dbPoolAttr.getUsername());
        p.setPassword(dbPoolAttr.getPassword());
        p.setUrl(dbPoolAttr.getUrl());
        p.setDriverClassName(dbPoolAttr.getDriverClassName());

        org.apache.tomcat.jdbc.pool.DataSource dataSource=new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setPoolProperties(p);
        return dataSource;
    }

    @Override
    public void init(DataSourceWapper dataSource)throws  Exception{
    }
    @Override
    public void configProperties(DataSource p, PoolConfig poolConfig, Map<String, Method> getMethods) {

    }

    @Override
    public void close(DataSourceWapper dataSource) throws Exception {
        ((org.apache.tomcat.jdbc.pool.DataSource)dataSource.getOriginalDataSource()).close();

    }
}