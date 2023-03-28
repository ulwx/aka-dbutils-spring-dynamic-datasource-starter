package com.github.ulwx.aka.dbutils.springboot.test.shardingjdbc_seata;

import com.github.ulwx.aka.dbutils.database.spring.MDataBaseFactory;
import com.github.ulwx.aka.dbutils.database.spring.MDataBaseTemplate;
import com.github.ulwx.aka.dbutils.database.utils.DbConst;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDataSourceAspect;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDynamicDataSource;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy(exposeProxy = true)
@Configuration
@ComponentScan
@Import(AkaDataSourceAspect.class)
public class MyConfiguration {

    @Bean
    public DataSourceTransactionManager transactionManager(AkaDynamicDataSource dynamicDataSource) {
        DataSourceTransactionManager dt = new DataSourceTransactionManager();
        dt.setDataSource(dynamicDataSource);
        return dt;
    }

    @Bean
    public MDataBaseFactory mDataBaseFactory(AkaDynamicDataSource dynamicDataSource) {
        MDataBaseFactory mDataBaseFactory = new MDataBaseFactory(dynamicDataSource);
        mDataBaseFactory.setTableColumRule(DbConst.TableNameRules.underline_to_camel);
        mDataBaseFactory.setTableNameRule(DbConst.TableColumRules.underline_to_camel);
        return mDataBaseFactory;
    }

    @Bean
    public MDataBaseTemplate mDataBaseTemplate(MDataBaseFactory mDataBaseFactory) {
        return new MDataBaseTemplate(mDataBaseFactory);
    }


}
