package com.github.ulwx.aka.dbutils.springboot.datasource;

import com.github.ulwx.aka.dbutils.spring.multids.AkaDynamicDataSource;
import com.github.ulwx.aka.dbutils.spring.multids.DataSourceInfo;
import org.springframework.beans.factory.DisposableBean;

import java.util.concurrent.ConcurrentHashMap;

public class AkaDynamicDataSourceBean extends AkaDynamicDataSource implements  DisposableBean {
    private AkaDbUtilsDynamicDataSourceProperties properties;
    public AkaDynamicDataSourceBean(AkaDbUtilsDynamicDataSourceProperties properties) {
        this.properties = properties;
    }


    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() {
        try {
            //初始化datasources
            AkaDataSourceFactory akaDataSourceFactory = AkaDataSourceFactory.getInstance();
            akaDataSourceFactory.build(properties.getDsType(), properties.getDatasources());
            ConcurrentHashMap<String, DataSourceInfo> map = akaDataSourceFactory.getDataSourceMap();
            this.setTargetDataSourceInfos(map);
            this.setDefaultTargetDataSourceName(properties.getDefaultDS());
            super.afterPropertiesSet();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
