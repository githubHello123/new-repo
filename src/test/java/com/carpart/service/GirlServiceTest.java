package com.carpart.service;

import com.carpart.Dao.GirlRepository;
import com.carpart.Entity.Girl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by Administrator on 2017/3/20.
 */
public class GirlServiceTest extends BaseTestService{
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GirlRepository girlRepository;

    @Test
    public void testGirlList() throws Exception {

        Girl girl = new Girl();
        girl.setAge(21);
        girl.setCupSize("A");

        girlRepository.save(girl);

        girl = new Girl();
        girl.setAge(27);
        girl.setCupSize("F");

        girlRepository.save(girl);

        List<Girl> list = girlRepository.findAll();
        for (Girl girl1 : list){
           log.info("girl="+girl1);
        }
    }
}