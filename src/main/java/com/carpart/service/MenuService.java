package com.carpart.service;

import com.carpart.entity.Menu;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
public interface MenuService {

    List<Menu> findAll();

    List<Menu> findByParentId(Long id);

    Menu findOne(Long id);

    void delete(Menu menu);

    Menu save(Menu menu);

    List<Menu> findParenMenus(String selfId,String id);
}
