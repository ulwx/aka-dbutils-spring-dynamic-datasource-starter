package com.github.ulwx.aka.dbutils.springboot.datasource.poolimpl;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.github.ulwx.aka.dbutils.spring.multids.DSPoolType;
import com.github.ulwx.aka.dbutils.spring.multids.DataSourceInfo;
import com.github.ulwx.aka.dbutils.springboot.datasource.AkaDataSourceFactory;
import com.github.ulwx.aka.dbutils.springboot.datasource.PoolConfig;
import com.github.ulwx.aka.dbutils.springboot.datasource.config.druid.DruidConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DruidDBPoolImpl extends DBPool {

    private static final Logger log = LoggerFactory.getLogger(DruidDBPoolImpl.class);
    @Override
    public DataSource configNewDataSource(DBPoolAttr dbPoolAttr) {
        DruidDataSource p = new DruidDataSource();
        p.setUsername(dbPoolAttr.getUsername());
        p.setPassword(dbPoolAttr.getPassword());
        p.setUrl(dbPoolAttr.getUrl());
        p.setDriverClassName(dbPoolAttr.getDriverClassName());


        return p;
    }

    @Override
    public void configProperties(DataSource p, PoolConfig poolConfig, Map<String, Method> getMethods) {
        DruidConfig druidConfig = (DruidConfig) poolConfig;
        List<Filter> filters = new ArrayList<>();

        if (druidConfig.getStat() != null) {
            filters.add(druidConfig.getStat());
        }
        if (druidConfig.getConfig() != null) {
            filters.add(druidConfig.getConfig());
        }
        if (druidConfig.getEncoding() != null) {
            filters.add(druidConfig.getEncoding());
        }
        if (druidConfig.getSlf4j() != null) {
            filters.add(druidConfig.getSlf4j());
        }
        if (druidConfig.getLog4j() != null) {
            filters.add(druidConfig.getLog4j());
        }
        if (druidConfig.getLog4j2() != null) {
            filters.add(druidConfig.getLog4j2());
        }
        if (druidConfig.getCommonsLog() != null) {
            filters.add(druidConfig.getCommonsLog());
        }
        if (druidConfig.getWall() != null) {
            filters.add(druidConfig.getWall());
        }
        ((DruidDataSource)p).setProxyFilters(filters);

        getMethods.remove("stat");
        getMethods.remove("config");
        getMethods.remove("encoding");
        getMethods.remove("slf4j");
        getMethods.remove("log4j");
        getMethods.remove("log4j2");
        getMethods.remove("commonsLog");
        getMethods.remove("wall");

    }

    @Override
    public void init(DataSourceInfo dataSource) throws Exception {
        try {
            ((DruidDataSource) dataSource.getOriginalDataSource()).init();
        } catch (SQLException e) {
            log.error(dataSource+","+dataSource.getOriginalDataSource(),e);
           // System.out.println(dataSource+","+dataSource.getOriginalDataSource());
            throw new RuntimeException(e+""+dataSource+","+dataSource.getOriginalDataSource());
        }
    }


    @Override
    public void close(DataSourceInfo dataSource) throws Exception {
        ((DruidDataSource) dataSource.getOriginalDataSource()).close();

    }

    @Override
    public String getType() {
        return DSPoolType.ALiBABA_DRUID;
    }
}
