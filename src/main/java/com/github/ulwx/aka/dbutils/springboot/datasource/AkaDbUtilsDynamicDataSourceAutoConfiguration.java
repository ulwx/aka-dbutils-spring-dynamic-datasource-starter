package com.github.ulwx.aka.dbutils.springboot.datasource;

import com.github.ulwx.aka.dbutils.spring.multids.AkaDatasourceAutoConfiguration;
import io.seata.spring.annotation.datasource.SeataProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(AkaDbUtilsDynamicDataSourceProperties.class)
@AutoConfigureAfter(AkaDatasourceAutoConfiguration.class)
public class AkaDbUtilsDynamicDataSourceAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(AkaDbUtilsDynamicDataSourceAutoConfiguration.class);
    private final AkaDbUtilsDynamicDataSourceProperties properties;
    public AkaDbUtilsDynamicDataSourceAutoConfiguration(AkaDbUtilsDynamicDataSourceProperties properties) {
        this.properties = properties;

    }
    @ConditionalOnMissingBean
    @ConditionalOnMissingClass("io.seata.spring.annotation.datasource.SeataProxy")
    @Bean
    public AkaDynamicDataSourceBean akaDynamicDataSource(AkaDbUtilsDynamicDataSourceProperties properties) {
        log.info("Init AkaDynamicDataSource");
        return new AkaDynamicDataSourceBean(properties);
    }

    @ConditionalOnMissingBean
    @ConditionalOnClass(name = "io.seata.spring.annotation.datasource.SeataProxy")
    @Bean
    public AkaDynamicDataSourceBean akaForSeataDynamicDataSource(AkaDbUtilsDynamicDataSourceProperties properties) {
        log.info("Init AkaDynamicDataSource");

        AkaDynamicDataSourceBean bean = new AkaDynamicDataSourceBean(properties);
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(bean);
        factory.setProxyTargetClass(true);
        Advisor advisor = new DefaultIntroductionAdvisor(new CustomIntroductionInterceptor(), SeataProxy.class);
        factory.addAdvisor(advisor);
        return (AkaDynamicDataSourceBean)factory.getProxy();

    }



    public static class CustomIntroductionInterceptor extends DelegatingIntroductionInterceptor
            implements SeataProxy {

    }
}
