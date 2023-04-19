package com.github.ulwx.aka.dbutils.springboot.test.seata;

import com.github.ulwx.aka.dbutils.spring.multids.AkaDataSourceContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyService.class);
    private AddressDao addressDao;


    public void init() {
        addressDao.init();
    }

    public MyService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @GlobalTransactional
    public void updateUseAkDs() {
        addressDao.updateMdForMaster2(1, "123");
        try {
            addressDao.updateMdForMaster1(2, "abc");
        } catch (Exception ex) {
            LOGGER.debug(ex + "");
        }
        int i=0;

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @GlobalTransactional
    public void updateRollback() {
        addressDao.updateMdForMaster2Required(1, "123");
        try {
            addressDao.updateMdForMaster1Required(2, "abc");
        } catch (Exception ex) {
            LOGGER.debug(ex + "");
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @GlobalTransactional
    public void updateDynamic() {
        AkaDataSourceContext.varPut("number",2);
        addressDao.updateMdDynamic2(1, "123");
        try {
            AkaDataSourceContext.varPut("number",1);
            addressDao.updateMdDynamic1(2, "abc");
        } catch (Exception ex) {
            LOGGER.debug(ex + "");
        }


    }

    @Transactional(propagation = Propagation.REQUIRED)
    @GlobalTransactional
    public void query() {
        Address address1= addressDao.getOne(1);
        Address address2= addressDao.getOne(2);

    }
}
