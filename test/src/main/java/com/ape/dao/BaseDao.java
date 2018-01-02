/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.dao;

import com.ape.utils.CommonUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * AngryApe created at 2017-11-21
 */
public abstract class BaseDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public  <T extends Serializable>int saveAll(List<T> entities) {
        if (CommonUtils.isEmpty(entities)) {
            return 0;
        }
        Session session = getSession();
        entities.forEach(f -> session.save(f));
        session.flush();
        return entities.size();
    }

}
