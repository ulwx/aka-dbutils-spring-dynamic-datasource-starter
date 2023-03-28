package com.github.ulwx.aka.dbutils.springboot.datasource;

import com.github.ulwx.aka.dbutils.spring.multids.DSPoolType;
import com.github.ulwx.aka.dbutils.springboot.datasource.poolimpl.*;

import java.util.HashMap;
import java.util.Map;

public class PoolFactory {
    public final static Map<String, DBPool> map = new HashMap<>();
    static{
        map.put(DSPoolType.TOMCAT_DB_POOL, TomcatDBPoolImpl.instance);
        map.put(DSPoolType.ALiBABA_DRUID, DruidDBPoolImpl.instance);
        map.put(DSPoolType.HikariCP, HikariDBPoolImpl.instance);
        map.put(DSPoolType.DBCP2, Dbcp2DBPoolImpl.instance);
        map.put(DSPoolType.ShardingJDBC, ShardingJDBCDBPoolImpl.instance);
    }
    public static DBPool getDBPool(String PoolType){
        return map.get(PoolType);
    }
}
