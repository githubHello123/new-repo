package com.carpart.service.impl;

import com.carpart.dao.GirlRepository;
import com.carpart.entity.Girl;
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

    @Override
    public Girl findOne(Long id) {
        return girlRepository.findOne(id);
    }

    @Override
    public void delete(Girl girl) {
        girlRepository.delete(girl);
    }

    @Override
    public Girl add(Girl girl) {
        return girlRepository.save(girl);
    }
}
