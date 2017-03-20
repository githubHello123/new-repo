package com.carpart.service.impl;

import com.carpart.Dao.GirlRepository;
import com.carpart.Entity.Girl;
import com.carpart.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
@Service
public class GirlServiceImpl implements GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Override
    public List<Girl> grilsAll() {
        return girlRepository.findAll();
    }
}
