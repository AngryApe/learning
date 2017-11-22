/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.dao;

import com.ape.entity.Metric;
import com.ape.utils.CommonUtils;
import com.ape.utils.PinyinUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AngryApe created at 2017-11-21
 */
@Repository
public class MetricDao extends BaseDao {

    public List<Metric> getAll() {
        System.out.println("Start query data.");
        String hql = "from Metric";
        Query query = getSession().createQuery(hql);
        List<Metric> list = query.list();
        System.out.println("End query data.");
        return list;
    }

    public void update(List<Metric> list) {
        System.out.println("Start update data.");
        Session session = getSession();
        list.forEach(m -> session.update(m));
        session.flush();
        System.out.println("End update data.");
    }

    public int cleanData() {
        System.out.println("Start clean data.");
        List<Metric> list = getAll();
        if (CommonUtils.isNotEmpty(list)) {
            list.forEach(
                    m -> m.setPy(PinyinUtil.getFirstLettersLowerCase(m.getName()).toLowerCase()));
            update(list);
        }
        System.out.println("End clean data.");
        return list.size();
    }
}
