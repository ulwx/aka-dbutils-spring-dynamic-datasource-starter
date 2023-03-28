package com.github.ulwx.aka.dbutils.springboot.datasource;

import com.github.ulwx.aka.dbutils.springboot.datasource.config.dbcp2.Dbcp2Config;
import com.github.ulwx.aka.dbutils.springboot.datasource.config.druid.DruidConfig;
import com.github.ulwx.aka.dbutils.springboot.datasource.config.hikari.HikariCpConfig;
import com.github.ulwx.aka.dbutils.springboot.datasource.config.shardingjdbc.ShardingJdbcConfig;
import com.github.ulwx.aka.dbutils.springboot.datasource.config.tomcatdbpool.TomcatDbConfig;

public class DataSourceConfig {
    private String dsName;
    private String username;
    private String password;
    private String driverClassName;
    private String url;
    private String type;
    private String refClass;
    private String checkTime;
    private String initCheckTime;

    private String seata; //XA 或AT

    private DruidConfig druid = null;


    private HikariCpConfig hikari = null;


    private Dbcp2Config dbcp2 = null;

    private ShardingJdbcConfig shardingJdbc=null;

    private TomcatDbConfig tomcatdbpool= null;

    public ShardingJdbcConfig getShardingJdbc() {
        return shardingJdbc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getRefClass() {
        return refClass;
    }

    public void setRefClass(String refClass) {
        this.refClass = refClass;
    }

    public void setShardingJdbc(ShardingJdbcConfig shardingJdbc) {
        this.shardingJdbc = shardingJdbc;
    }

    public TomcatDbConfig getTomcatdbpool() {
        return tomcatdbpool;
    }

    public void setTomcatdbpool(TomcatDbConfig tomcatdbpool) {
        this.tomcatdbpool = tomcatdbpool;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getSeata() {
        return seata;
    }

    public void setSeata(String seata) {
        this.seata = seata;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getInitCheckTime() {
        return initCheckTime;
    }

    public void setInitCheckTime(String initCheckTime) {
        this.initCheckTime = initCheckTime;
    }
}
