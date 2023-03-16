package com.github.ulwx.aka.springboot.datasource;

import com.github.ulwx.aka.dbutils.spring.multids.AkaDynamicDataSource;
import org.springframework.beans.factory.DisposableBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class AkaDynamicDataSourceBean extends AkaDynamicDataSource implements  DisposableBean {
    private AkaDbUtilsDynamicDataSourceProperties properties;
    public AkaDynamicDataSourceBean(AkaDbUtilsDynamicDataSourceProperties properties) {
        this.properties = properties;
    }


    public void destroy() throws Exception {

    }

    public void afterPropertiesSet() {
        try {
            //初始化datasources
            AkaDataSourceFactory akaDataSourceFactory = AkaDataSourceFactory.getInstance();
            akaDataSourceFactory.build(properties.getDsType(), properties.getDatasources());
            Map<String, DataSourceWapper> map = akaDataSourceFactory.getDataSourceMap();
            Map<String, DataSource> targetDataSource = new HashMap<>();
            for(String key:map.keySet()){
                targetDataSource.put(key,map.get(key).getDataSource());
            }
            this.setTargetDataSources(targetDataSource);
            this.setDefaultTargetDataSourceName(properties.getDefaultDS());
            super.afterPropertiesSet();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
