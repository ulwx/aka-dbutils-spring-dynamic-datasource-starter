package com.github.ulwx.aka.springboot.datasource.akadb;

import com.github.ulwx.aka.dbutils.spring.multids.AkaDataSourceContext;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTest {
    @Autowired
    MyService myService;
    @Autowired
    AddressDao addressDao;
    @Test
    public  void testDefalut() throws Exception{

        myService.init();
        myService.updateMdb();
        Address address1=addressDao.getListMd(1);
        Address address2= addressDao.getListMd(2);
        Assert.assertTrue(address1.getAddressId()==1 && address1.getName().equals("123"));
        Assert.assertTrue(address2.getAddressId()==2 && address2.getName().equals("2"));


    }
    @Test
    public  void testUseAkDs() throws Exception{

        AkaDataSourceContext.executeMethod("master1",()->{
            myService.init();
        });
        AkaDataSourceContext.executeMethod("master2",()->{
            myService.init();
        });

        myService.updateMdbUseAkDs();

        AkaDataSourceContext.executeMethod("master1",()->{
            Address address=addressDao.getListMd(2);
            Assert.assertTrue(address.getAddressId()==2 && address.getName().equals("abc"));
        });
        AkaDataSourceContext.executeMethod("master2",()->{
            Address address= addressDao.getListMd(1);
            Assert.assertTrue(address.getAddressId()==1 && address.getName().equals("123"));
        });


    }

    @Test
    public  void testRollback() throws Exception{

        AkaDataSourceContext.executeMethod("master1",()->{
            myService.init();
        });
        AkaDataSourceContext.executeMethod("master2",()->{
            myService.init();
        });

        try {
            myService.updateMdbRollback();
        }catch (Exception e){
            System.out.println(e);
        }

        AkaDataSourceContext.executeMethod("master1",()->{
            Address address=addressDao.getListMd(2);
            Assert.assertTrue(address.getAddressId()==2 && address.getName().equals("2"));
        });
        AkaDataSourceContext.executeMethod("master2",()->{
            Address address= addressDao.getListMd(1);
            Assert.assertTrue(address.getAddressId()==1 && address.getName().equals("1"));
        });


    }

}
