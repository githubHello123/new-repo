package com.carpart.dao.impl;

import com.carpart.dao.IGenericBaseCommonDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/21.
 */
public abstract class GenericBaseCommonDao<T, PK extends Serializable> implements IGenericBaseCommonDao{

    private SessionFactory sessionFactory;

    public org.hibernate.Session getSession() {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        SessionFactory sessionFactory = (SessionFactory) wac.getBean("sessionFactory");
        // 事务必须是开启的(Required)，否则获取不到
        return sessionFactory.getCurrentSession();
    }

    /**
     * 根据Id获取对象。
     */
    public <T> T get(Class<T> entityClass, final Serializable id) {
        return (T) getSession().get(entityClass, id);

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
