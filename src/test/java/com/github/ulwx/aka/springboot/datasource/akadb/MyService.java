package com.github.ulwx.aka.springboot.datasource.akadb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyService.class);
    private AddressDao addressDao;


    public void init(){
        addressDao.init();
    }
    public MyService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMdb(){
        addressDao.updateMd1(1,"123");
        try {
            addressDao.updateMd2(2, "abc");
        }catch (Exception ex){
            LOGGER.debug(ex+"");
        }
        int i=0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMdbUseAkDs(){
        addressDao.updateMdForMaster2(1,"123");
        try {
            addressDao.updateMdForMaster1(2, "abc");
        }catch (Exception ex){
            LOGGER.debug(ex+"");
        }
        int i=0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMdbRollback(){
        addressDao.updateMdForMaster2Required(1,"123");
        try {
            addressDao.updateMdForMaster1Required(2, "abc");
        }catch (Exception ex){
            LOGGER.debug(ex+"");
        }
        int i=0;
    }

}
