package com.github.ulwx.aka.dbutils.springboot.test.seata;

import com.github.ulwx.aka.dbutils.spring.multids.AkaDataSourceContext;
import org.junit.Assert;
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

        AkaDataSourceContext.executeMethod("master-a-at",()->{
            myService.init();
        });
        AkaDataSourceContext.executeMethod("master-b-at",()->{
            myService.init();
        });

        myService.updateUseAkDs();

        AkaDataSourceContext.executeMethod("master-a-at",()->{
            Address address=addressDao.getListMd(2);
            Assert.assertTrue(address.getAddressId()==2 && address.getName().equals("abc"));
        });
        AkaDataSourceContext.executeMethod("master-b-at",()->{
            Address address= addressDao.getListMd(1);
            Assert.assertTrue(address.getAddressId()==1 && address.getName().equals("123"));
        });



    }

    @Test
    public  void testRollback() throws Exception{

        AkaDataSourceContext.executeMethod("master-a-at",()->{
            myService.init();
        });
        AkaDataSourceContext.executeMethod("master-b-at",()->{
            myService.init();
        });

        try {
            myService.updateRollback();
        }catch (Exception e){
            System.out.println(e);
        }

        AkaDataSourceContext.executeMethod("master-a-at",()->{
            Address address=addressDao.getListMd(2);
            Assert.assertTrue(address.getAddressId()==2 && address.getName().equals("2"));
        });
        AkaDataSourceContext.executeMethod("master-b-at",()->{
            Address address= addressDao.getListMd(1);
            Assert.assertTrue(address.getAddressId()==1 && address.getName().equals("1"));
        });

        Thread.sleep(15000);

    }

    @Test
    public  void testUseDynamic() throws Exception{

        AkaDataSourceContext.executeMethod("master-a-at",()->{
            myService.init();
        });
        AkaDataSourceContext.executeMethod("master-b-at",()->{
            myService.init();
        });

        try {
            myService.updateDynamic();
        }catch (Exception e){
            System.out.println(e);
        }
        AkaDataSourceContext.executeMethod("master-a-at",()->{
            Address address=addressDao.getListMd(2);
            Assert.assertTrue(address.getAddressId()==2 && address.getName().equals("2"));
        });
        AkaDataSourceContext.executeMethod("master-b-at",()->{
            Address address= addressDao.getListMd(1);
            Assert.assertTrue(address.getAddressId()==1 && address.getName().equals("1"));
        });
        Thread.sleep(15000);

    }



}
