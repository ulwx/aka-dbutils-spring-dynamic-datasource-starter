package com.github.ulwx.aka.dbutils.springboot.test.remote;

import com.github.ulwx.aka.dbutils.springboot.datasource.DBPoolAttrSource;
import com.github.ulwx.aka.dbutils.springboot.datasource.IDBPoolAttrSourceConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IDBPoolAttrSourceConfigurerImpl implements IDBPoolAttrSourceConfigurer {
    private static final Logger log = LoggerFactory.getLogger(IDBPoolAttrSourceConfigurerImpl.class);
    private static volatile  int number = 0;
    @Override
    public void configProperties(String dsName,DBPoolAttrSource attrSource) {
        log.debug("++++dsName:"+dsName);
        String url1="jdbc:mysql://localhost:3306/test1?characterEncoding=utf8&useSSL=false";
        String url2="jdbc:mysql://localhost:3306/test2?characterEncoding=utf8&useSSL=false";
        String[] urls=new String[]{url1,url2};
        number=number % 2 ;
        attrSource.setUrl(urls[number]);
        attrSource.setUsername("root");
        attrSource.setPassword("123456");
        attrSource.setDriverClassName("com.mysql.jdbc.Driver");

        number++;



    }
}
