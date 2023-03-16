package com.github.ulwx.aka.springboot.datasource.poolimpl;

import java.util.HashMap;
import java.util.Map;

public class PoolType {
    public final static String TOMCAT_DB_POOL = "tomcatdbpool";
    public final static String ALiBABA_DRUID="druid";
    public final static String HikariCP="hikari";
    public final static String DBCP2="dbcp2";
    public final static String ShardingJDBC="ShardingJDBC";
    public final static Map<String, DBPool> map = new HashMap<>();
    static{
        map.put(TOMCAT_DB_POOL, TomcatDBPoolImpl.instance);
        map.put(ALiBABA_DRUID, DruidDBPoolImpl.instance);
        map.put(HikariCP, HikariDBPoolImpl.instance);
        map.put(DBCP2, HikariDBPoolImpl.instance);
        map.put(ShardingJDBC, ShardingJDBCDBPoolImpl.instance);
    }

    public static DBPool getDBPool(String PoolType){
        return map.get(PoolType);
    }
}
