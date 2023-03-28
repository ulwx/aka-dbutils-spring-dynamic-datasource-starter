package com.github.ulwx.aka.dbutils.springboot.datasource;

import java.util.Objects;

public  class DBPoolAttrSource {
    /**
     * 数据源连接的用户名
     */
    private String username;
    /**
     * 数据源连接的用户密码
     */
    private String password;
    /**
     * 数据源连接的driver class
     */
    private String driverClassName;
    /**
     * 数据源连接地址
     */
    private String url;




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


    public DBPoolAttrSource() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBPoolAttrSource that = (DBPoolAttrSource) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(driverClassName, that.driverClassName)
                && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, driverClassName, url);
    }
}
