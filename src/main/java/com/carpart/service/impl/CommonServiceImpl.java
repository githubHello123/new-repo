package com.carpart.service.impl;

import com.carpart.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/21.
 */
@Service("commonServiceImpl")
@Transactional
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonService commonService;

    @Override
    public <T> T get(Class<T> class1, Serializable id) {
        return commonService.get(class1,id);
    }
}
