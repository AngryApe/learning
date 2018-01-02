/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.test.database;

import com.ape.service.MetricService;
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
    private MetricService metricService;

    @Test
    public void metricPyReplace() {
        Assert.assertEquals(377, metricService.cleanData());
    }

    @Test
    public void metricGenerate(){
        try {
            metricService.generateData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);
    }
}
