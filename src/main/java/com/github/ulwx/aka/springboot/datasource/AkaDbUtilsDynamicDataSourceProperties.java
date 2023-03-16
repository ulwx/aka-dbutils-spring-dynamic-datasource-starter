package com.github.ulwx.aka.springboot.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.LinkedHashMap;
import java.util.Map;

@ConfigurationProperties(prefix = AkaDbUtilsDynamicDataSourceProperties.PREFIX)
public class AkaDbUtilsDynamicDataSourceProperties {
    public static final String PREFIX = "spring.datasource.aka-dynamic";

    private String defaultDS ="";
    @NestedConfigurationProperty
    private DsTypeConfig dsType=new DsTypeConfig();
    private Map<String, DataSourceConfig> datasources = new LinkedHashMap<>();

    public String getDefaultDS() {
        return defaultDS;
    }

    public void setDefaultDS(String defaultDS) {
        this.defaultDS = defaultDS;
    }

    public Map<String, DataSourceConfig> getDatasources() {
        return datasources;
    }

    public void setDatasources(Map<String, DataSourceConfig> datasources) {
        this.datasources = datasources;
    }

    public DsTypeConfig getDsType() {
        return dsType;
    }

    public void setDsType(DsTypeConfig dsType) {
        this.dsType = dsType;
    }


}