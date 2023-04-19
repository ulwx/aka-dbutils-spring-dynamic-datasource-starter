package com.github.ulwx.aka.dbutils.springboot.test.shardingjdbc_seata;

import com.github.ulwx.aka.dbutils.spring.multids.AkaDataSourceContext;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTest {
    @Autowired
    MyService myService;
    @Autowired
    AddressDao addressDao;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public  void testUseAkDs() throws Exception{

        AkaDataSourceContext.executeMethod("sharding-ds",()->{
            myService.init();
        });

        myService.updateUseAkDs();

        AkaDataSourceContext.executeMethod("sharding-ds",()->{
            Address address=addressDao.getOneAddress(1);
            Assert.assertTrue(address.getAddressId()==1 && address.getName().equals("123"));
             address=addressDao.getOneAddress(2);
            Assert.assertTrue(address.getAddressId()==2 && address.getName().equals("abc"));
        });

        Thread.sleep(15000);


    }

    @Test
    public  void testRollback() throws Exception{

        AkaDataSourceContext.executeMethod("sharding-ds",()->{
            myService.init();
        });


        try {
            myService.updateRollback();
        }catch (Exception e){
            System.out.println(e);
        }

        AkaDataSourceContext.executeMethod("sharding-ds",()->{
            Address address=addressDao.getOneAddress(2);
            Assert.assertTrue(address.getAddressId()==2 && address.getName().equals("2"));
        });

        Thread.sleep(15000);

    }



}
