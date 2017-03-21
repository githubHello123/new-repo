package com.carpart.dao;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/21.
 */
public interface IGenericBaseCommonDao {

    /**
     * 根据实体名称和主键获取实体
     *
     * @param <T>
     * @param entityName
     * @param id
     * @return
     */
    <T> T get(Class<T> entityName, Serializable id);
}
