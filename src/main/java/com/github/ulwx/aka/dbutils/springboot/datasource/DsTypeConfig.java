package com.github.ulwx.aka.dbutils.springboot.datasource;

import com.github.ulwx.aka.dbutils.springboot.datasource.config.dbcp2.Dbcp2Config;
import com.github.ulwx.aka.dbutils.springboot.datasource.config.druid.DruidConfig;
import com.github.ulwx.aka.dbutils.springboot.datasource.config.hikari.HikariCpConfig;
import com.github.ulwx.aka.dbutils.springboot.datasource.config.shardingjdbc.ShardingJdbcConfig;
import com.github.ulwx.aka.dbutils.springboot.datasource.config.tomcatdbpool.TomcatDbConfig;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

public class DsTypeConfig {

    @NestedConfigurationProperty
    private DruidConfig druid = null;
    @NestedConfigurationProperty
    private HikariCpConfig hikari = null;

    @NestedConfigurationProperty
    private Dbcp2Config dbcp2 = null;
    @NestedConfigurationProperty
    private ShardingJdbcConfig shardingJdbc=null;
    @NestedConfigurationProperty
    private TomcatDbConfig tomcatdbpool= null;

    public ShardingJdbcConfig getShardingJdbc() {
        return shardingJdbc;
    }

    public void setShardingJdbc(ShardingJdbcConfig shardingJdbc) {
        this.shardingJdbc = shardingJdbc;
    }

    public DruidConfig getDruid() {
        return druid;
    }

    public void setDruid(DruidConfig druid) {
        this.druid = druid;
    }

    public HikariCpConfig getHikari() {
        return hikari;
    }

    public void setHikari(HikariCpConfig hikari) {
        this.hikari = hikari;
    }

    public Dbcp2Config getDbcp2() {
        return dbcp2;
    }

    public void setDbcp2(Dbcp2Config dbcp2) {
        this.dbcp2 = dbcp2;
    }

    public TomcatDbConfig getTomcatdbpool() {
        return tomcatdbpool;
    }

    public void setTomcatdbpool(TomcatDbConfig tomcatdbpool) {
        this.tomcatdbpool = tomcatdbpool;
    }
}
