package com.carpart.service.impl;

import com.carpart.dao.MenuRepository;
import com.carpart.entity.Menu;
import com.carpart.service.MenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2017/3/22 0022.
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public List<Menu> findByParentId(Long id) {
        return menuRepository.findByParentFunctionId(id);
    }

    @Override
    public Menu findOne(Long id) {
        return menuRepository.findOne(id);
    }

    @Override
    public void delete(Menu menu) {
        menuRepository.delete(menu);
    }

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> findParenMenus(String selfId,String id) {
        Specification<Menu> specification = getWhereClause(selfId,id);
        return menuRepository.findAll(specification);
    }

    public static Specification<Menu> getWhereClause(String selfId,String id) {
        return new Specification<Menu>() {
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(selfId)){
                    list.add(cb.notEqual(root.get("id").as(Long.class), Long.valueOf(selfId)));
                }
                if(StringUtils.isNotBlank(id)){
                    list.add(cb.equal(root.get("menu").get("id").as(Long.class), Long.valueOf(id)));
                }else{
                    list.add(cb.isNull(root.get("menu")));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }
}
