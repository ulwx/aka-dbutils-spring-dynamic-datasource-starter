package com.github.ulwx.aka.dbutils.springboot.test.remote;

import com.github.ulwx.aka.dbutils.spring.multids.AkaDataSourceContext;
import com.github.ulwx.aka.dbutils.spring.multids.DataSourceInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTest {
    @Autowired
    MyService myService;
    @Autowired
    AddressDao addressDao;

    @Test
    public  void testUseAkDs() throws Exception{

        AkaDataSourceContext.executeMethod("master1",()->{
            myService.init();
            myService.update();
        });
        AkaDataSourceContext.executeMethod("master2",()->{
            myService.init();
            myService.update();
        });


        AkaDataSourceContext.executeMethod("remote-ds-jdbc",()->{
            Address address=addressDao.getListMd(1);
            DataSourceInfo dataSourceInfo1=AkaDataSourceContext.getCurrentDS();
            try {
                System.out.println("dataSourceInfo1="+dataSourceInfo1);

            } catch (Exception e) {
            }
            address=addressDao.getListMd(1);
            DataSourceInfo dataSourceInfo2=AkaDataSourceContext.getCurrentDS();
            System.out.println("dataSourceInfo2="+dataSourceInfo2);

        });

        int i=0;
    }




}
