/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.test.database;

import com.ape.dao.MetricDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * AngryApe created at 2017-11-21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-context.xml")
public class MySqlTest {

    @Autowired
    private MetricDao metricDao;

    @Test
    public void metricPyReplace() {
        Assert.assertEquals(140, metricDao.cleanData());
    }
}
