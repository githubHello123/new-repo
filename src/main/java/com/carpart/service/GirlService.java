package com.carpart.service;

import com.carpart.entity.Girl;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
public interface GirlService {

    List<Girl> grilsAll();

    Girl findOne(Long id);

    void delete(Girl girl);

    Girl add(Girl girl);
}
