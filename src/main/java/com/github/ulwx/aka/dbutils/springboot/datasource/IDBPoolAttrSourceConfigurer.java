package com.github.ulwx.aka.dbutils.springboot.datasource;

/**
 * 获取数据源接口
 */
public interface IDBPoolAttrSourceConfigurer {

    void configProperties(String dsName,DBPoolAttrSource attrSource);
}
