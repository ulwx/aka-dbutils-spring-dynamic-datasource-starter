package com.github.ulwx.aka.springboot.datasource.akadb;

import com.github.ulwx.aka.dbutils.database.spring.MDataBaseTemplate;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.dbutils.tool.MD;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Component
public class AddressDao {

    private MDataBaseTemplate mDataBaseTemplate;

    public AddressDao(MDataBaseTemplate mDataBaseTemplate){

        this.mDataBaseTemplate=mDataBaseTemplate;
    }
    public void init(){
        mDataBaseTemplate.exeScript("", "test.sql",
                false,true,";","utf-8");

    }
    public Address getListMd(int id){
        Map<String, Object> mp=new HashMap<>();
        mp.put("id",id);
        Address address=mDataBaseTemplate.queryOne(Address.class,
                MD.md(),mp);
        return address;

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMd1(int id,String name){
        update(id,name);

    }
    @Transactional(propagation = Propagation.NESTED)
    public void updateMd2(int id,String name){
        update(id,name);
        throw new RuntimeException();

    }
    @AkaDS("master1")
    public void updateMdForMaster1(int id,String name){
        update(id,name);
        throw new RuntimeException();

    }
    @AkaDS("master2")
    public void updateMdForMaster2(int id,String name){
        update(id,name);

    }
    @AkaDS("master1")
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMdForMaster1Required(int id,String name){
        update(id,name);
        throw new RuntimeException();

    }
    @AkaDS("master2")
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMdForMaster2Required(int id,String name){
        update(id,name);

    }

    public void update(int id,String name){
        Map<String, Object> mp=new HashMap<>();
        mp.put("name",name);
        mp.put("id",id);
        mDataBaseTemplate.update(MD.md(),mp);
    }
}
