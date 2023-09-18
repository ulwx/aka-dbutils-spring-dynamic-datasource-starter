package com.github.ulwx.aka.dbutils.springboot.datasource;

import com.github.ulwx.aka.dbutils.spring.multids.DSPoolType;
import com.github.ulwx.aka.dbutils.springboot.datasource.poolimpl.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PoolFactory {
    public final static Map<String, DBPool> map = new ConcurrentHashMap<>();

    public static DBPool getDBPool(String PoolType){

        DBPool pool= map.get(PoolType);
        if(pool==null){
            synchronized (PoolFactory.class){
                pool= map.get(PoolType);
                if(pool==null){
                    //map.put(PoolType,)
                    if(PoolType.equals(DSPoolType.TOMCAT_DB_POOL)){
                        pool=new TomcatDBPoolImpl();
                    }else if(PoolType.equals(DSPoolType.ALiBABA_DRUID)){
                        pool=new DruidDBPoolImpl();
                    }if(PoolType.equals(DSPoolType.HikariCP)){
                        pool=new HikariDBPoolImpl();
                    }if(PoolType.equals(DSPoolType.DBCP2)){
                        pool=new Dbcp2DBPoolImpl();
                    }if(PoolType.equals(DSPoolType.ShardingJDBC)){
                        pool=new ShardingJDBCDBPoolImpl();
                    }
                    map.put(PoolType,pool);
                }

            }


        }
        return pool;
    }
}
