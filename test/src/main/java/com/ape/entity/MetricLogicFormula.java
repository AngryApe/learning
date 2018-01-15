/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 逻辑数据项公式
 * AngryApe created at 2017-11-27
 */
@Entity
@Table(name = "t_busi_metric_item_formula")
public class MetricLogicFormula {

    @Id
    private Integer id;

    @Column
    private String metricCode;

    @Column
    private String formulaClass;

    @Column
    private Integer parameters;

    @Column
    private String owner;

    @Column
    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMetricCode() {
        return metricCode;
    }

    public void setMetricCode(String metricCode) {
        this.metricCode = metricCode;
    }

    public String getFormulaClass() {
        return formulaClass;
    }

    public void setFormulaClass(String formulaClass) {
        this.formulaClass = formulaClass;
    }

    public Integer getParameters() {
        return parameters;
    }

    public void setParameters(Integer parameters) {
        this.parameters = parameters;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
