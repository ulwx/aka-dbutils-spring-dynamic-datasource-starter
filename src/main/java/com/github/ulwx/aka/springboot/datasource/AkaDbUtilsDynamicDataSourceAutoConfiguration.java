package com.github.ulwx.aka.springboot.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(AkaDbUtilsDynamicDataSourceProperties.class)
public class AkaDbUtilsDynamicDataSourceAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(AkaDbUtilsDynamicDataSourceAutoConfiguration.class);
    private final AkaDbUtilsDynamicDataSourceProperties properties;
    public AkaDbUtilsDynamicDataSourceAutoConfiguration(AkaDbUtilsDynamicDataSourceProperties properties) {
        this.properties = properties;

    }

    @ConditionalOnMissingBean
    @Bean
    public AkaDynamicDataSourceBean akaDataSource(AkaDbUtilsDynamicDataSourceProperties properties) {
        log.info("Init AkaDynamicDataSource");

        return new AkaDynamicDataSourceBean(properties);
    }
}
