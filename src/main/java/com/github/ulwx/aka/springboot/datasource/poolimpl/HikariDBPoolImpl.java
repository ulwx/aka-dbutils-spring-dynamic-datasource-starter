package com.github.ulwx.aka.springboot.datasource.poolimpl;

import com.github.ulwx.aka.springboot.datasource.DataSourceWapper;
import com.github.ulwx.aka.springboot.datasource.PoolConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Map;

public class HikariDBPoolImpl extends DBPool {
    public static HikariDBPoolImpl instance=new HikariDBPoolImpl();

    @Override
    public void init(DataSourceWapper dataSource) throws Exception {

    }

    @Override
    public void configProperties(DataSource p, PoolConfig poolConfig, Map<String, Method> getMethods) {

    }

    @Override
    public DataSource configNewDataSource(DBPoolAttr dbPoolAttr) {
        HikariDataSource p=new HikariDataSource();
        p.setUsername(dbPoolAttr.getUsername());
        p.setPassword(dbPoolAttr.getPassword());
        p.setJdbcUrl(dbPoolAttr.getUrl());
        p.setDriverClassName(dbPoolAttr.getDriverClassName());
        return p;
    }


    @Override
    public void close(DataSourceWapper dataSource) throws Exception {
        ((HikariDataSource) dataSource.getOriginalDataSource()).close();

    }

    @Override
    public String getType() {
        return PoolType.HikariCP;
    }
}
