package com.github.ulwx.aka.springboot.datasource.poolimpl;

import com.github.ulwx.aka.springboot.datasource.PoolConfig;

public  class DBPoolAttr {
    private String dsName;
    private String seata; //XA 或AT
    private String username;
    private String password;
    private String driverClassName;
    private String url;

    private  PoolConfig attributes;
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

    public String getSeata() {
        return seata;
    }

    public void setSeata(String seata) {
        this.seata = seata;
    }

    public DBPoolAttr() {

    }

    public PoolConfig getAttributes() {
        return attributes;
    }

    public void setAttributes(PoolConfig attributes) {
        this.attributes = attributes;
    }
}
