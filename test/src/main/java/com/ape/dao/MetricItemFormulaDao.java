/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

/**
 * AngryApe created at 2017-11-27
 */
@Repository("metricItemFormulaDao")
public class MetricItemFormulaDao extends BaseDao {

    public boolean truncate() {
        String hql = "delete from MetricItemFormula";
        Query query = getSession().createQuery(hql);
        int num = query.executeUpdate();
        return num > 0;
    }
}
