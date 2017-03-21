package com.carpart.service;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/21.
 */
public interface CommonService {
    /**
     * 根据实体名称和主键获取实体
     *
     * @param <T>class1
     * @param id
     * @return
     */
    public <T> T get(Class<T> class1, Serializable id);
}
