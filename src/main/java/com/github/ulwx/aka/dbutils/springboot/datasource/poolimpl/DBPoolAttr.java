package com.github.ulwx.aka.dbutils.springboot.datasource.poolimpl;

import com.github.ulwx.aka.dbutils.springboot.datasource.PoolConfig;

public  class DBPoolAttr {
    private String dsName;
    private String seata; //XA 或AT
    private String username;
    private String password;
    private String driverClassName;
    private String url;
    private String type;
    private String refClass;
    private Boolean enable;

    /**
     * 检测时间，单位秒。用于远程获取配置时，检测当前的配置和远程（如数据库）存储的是否一致，不一致则用远程的替换当前
     * 配置，再重新构造数据源。例如设置10，则表示每10秒去重新调用IDBPoolAttrSourceConfigurer#configProperties()方法，
     * 根据返回的结果（attrSource）和当前相应数据源的配置作比较。
     */
    private String checkTime;
    private String initCheckTime;

    private PoolConfig attributes;

    public static class TYPE{
        public static final String local="local";
        public static final String custom="custom";
    }

    public String getCheckTime() {
        return checkTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
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

    public String getInitCheckTime() {
        return initCheckTime;
    }

    public void setInitCheckTime(String initCheckTime) {
        this.initCheckTime = initCheckTime;
    }
}
