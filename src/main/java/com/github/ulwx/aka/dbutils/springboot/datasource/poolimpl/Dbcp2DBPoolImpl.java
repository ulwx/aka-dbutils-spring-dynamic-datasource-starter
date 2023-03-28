package com.github.ulwx.aka.dbutils.springboot.datasource.poolimpl;

import com.github.ulwx.aka.dbutils.spring.multids.DSPoolType;
import com.github.ulwx.aka.dbutils.spring.multids.DataSourceInfo;
import com.github.ulwx.aka.dbutils.springboot.datasource.PoolConfig;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Map;

public class Dbcp2DBPoolImpl extends DBPool {
    public static Dbcp2DBPoolImpl instance=new Dbcp2DBPoolImpl();


    @Override
    public String getType() {
        return DSPoolType.DBCP2;
    }

    @Override
    public DataSource configNewDataSource(DBPoolAttr dbPoolAttr){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(dbPoolAttr.getUsername());
        dataSource.setPassword(dbPoolAttr.getPassword());
        dataSource.setUrl(dbPoolAttr.getUrl());
        dataSource.setDriverClassName(dbPoolAttr.getDriverClassName());
        return dataSource;
    }

    @Override
    public void init(DataSourceInfo dataSource)throws  Exception{
    }
    @Override
    public void configProperties(DataSource p, PoolConfig poolConfig, Map<String, Method> getMethods) {

    }

    @Override
    public void close(DataSourceInfo dataSource) throws Exception {
        ((BasicDataSource)dataSource.getOriginalDataSource()).close();

    }
}
