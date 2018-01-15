/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.service;

import com.ape.dao.MetricDao;
import com.ape.dao.MetricFormulaParamDao;
import com.ape.dao.MetricItemFormulaDao;
import com.ape.entity.Metric;
import com.ape.entity.MetricFormulaParam;
import com.ape.entity.MetricItemFormula;
import com.ape.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * AngryApe created at 2017-11-28
 */
@Service("metricService")
public class MetricService {

    Logger logger = LoggerFactory.getLogger(MetricService.class);

    private Map<String, Metric> metricMap = new HashMap<>();
    private Map<String, Metric> metricNameMap = new HashMap<>();

    private Map<String, MetricItemFormula> formulaMap = new HashMap<>();

    private Map<String, List<MetricFormulaParam>> paramMap = new HashMap<>();

    private List<String> incrementFunc = Arrays
            .asList("ElectricityHour", "ElectricityDay", "ElectricityMonth");

    @Autowired
    private MetricDao metricDao;
    @Autowired
    private MetricItemFormulaDao metricItemFormulaDao;
    @Autowired
    private MetricFormulaParamDao metricFormulaParamDao;

    public int cleanData() {
        return metricDao.cleanData();
    }

    public void generateData() {
        //取所有Metric并缓存
        List<Metric> metrics = metricDao.getAll();
        if (CommonUtils.isEmpty(metrics)) {
            System.out.println("Could not get any Metric record, please check.");
            return;
        }
        metrics.forEach(m -> {
            metricMap.put(m.getCode(), m);
            metricNameMap.put(m.getName()+m.getBelong(), m);
        });
        //先删除所有公式及参数
        metricItemFormulaDao.truncate();
        metricFormulaParamDao.truncate();
        //再重新生成
        buildEntity();
        //保存
        System.out.println("=========================");
        List<MetricItemFormula> formulas = new ArrayList<>();
        formulas.addAll(formulaMap.values());
        metricItemFormulaDao.saveAll(formulas);
        System.out.println("生成公式个数：" + formulas.size());

        formulas.forEach(f -> System.out.println(f));
        System.out.println("=========================");
        List<MetricFormulaParam> params = new ArrayList<>();
        paramMap.values().forEach(v -> params.addAll(v));
        metricFormulaParamDao.saveAll(params);
        System.out.println("生成参数个数：" + params.size());
        params.forEach(f -> System.out.println(f));
    }

    private void buildEntity() {
        final int[] count = {0};
        metricMap.keySet().stream().filter(key -> {
            Metric metric = metricMap.get(key);
            String[] conf = FormulaConf.get(metric.getName()+metric.getBelong());
            return conf != null && CommonUtils.isNotEmpty(conf[0]);
        }).forEach(key -> {
            Metric metric = metricMap.get(key);
            String[] conf = FormulaConf.get(metric.getName()+metric.getBelong());
            //生成formula
            MetricItemFormula formula = buildFormula(metric, conf);
            formulaMap.put(key, formula);
            //生成参数
            List<MetricFormulaParam> params = buildParams(metric, conf);
            paramMap.put(key, params);
            count[0]++;
        });
        System.out.println("需要生成公式的数据项个数：" + count[0]);
    }

    private MetricItemFormula buildFormula(Metric metric, String[] conf) {
        MetricItemFormula formula = new MetricItemFormula();
        formula.setCode(metric.getCode());
        formula.setOwner(metric.getOwner());
        formula.setMemo("");
        formula.setParameters(conf.length - 1);
        formula.setFormulaClass(conf[0]);
        return formula;
    }

    private List<MetricFormulaParam> buildParams(Metric metric, String[] conf) {
        List<MetricFormulaParam> params = new ArrayList<>();
        for (int i = 1; i < conf.length; i++) {
            String paramStr = conf[i];
            Metric paramM = metricNameMap.get(paramStr);
            if (paramM == null) {
                logger.warn("参数生成失败，metricCode={},paramName={}", metric.getCode(), paramStr);
                System.exit(0);
            }
            MetricFormulaParam param = new MetricFormulaParam();
            param.setCode(metric.getCode());
            param.setRefCode(paramM.getCode());
            param.setFactor(1D);
            param.setConstant(0D);
            param.setHistoryNum(incrementFunc.contains(conf[0]) ? 1 : 0);
            params.add(param);
        }
        return params;
    }

}
