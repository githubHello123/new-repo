package com.carpart.service;

import com.carpart.Entity.Girl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
public interface GirlService {

    List<Girl> grilsAll();

    Girl findById(Long id);

    void delete(Girl girl);
}
