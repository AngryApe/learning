/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 逻辑数据项公式参数
 * table: t_busi_metric_item_formula_params
 * AngryApe created at 2017-11-24
 */
@Entity
@Table(name = "t_busi_metric_item_formula_params")
public class MetricFormulaParam implements Serializable {

    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer id;
    /**
     * 数据项编码
     */
    @Column(name = "metric_code")
    private String code;
    /**
     * 引用的数据项
     */
    @Column(name = "cmetric_code")
    private String refCode;
    /**
     * 系数
     */
    @Column(name = "factor")
    private Double factor;
    /**
     * 常量
     */
    @Column(name = "constants")
    private Double constant;
    /**
     * 取历史数据的周期
     */
    @Column(name = "history_nums")
    private Integer historyNum;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public Double getConstant() {
        return constant;
    }

    public void setConstant(Double constant) {
        this.constant = constant;
    }

    public Integer getHistoryNum() {
        return historyNum;
    }

    public void setHistoryNum(Integer historyNum) {
        this.historyNum = historyNum;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("code=").append(code).append(",refCode=").append(refCode).append(",factor=")
                .append(factor).append(",constant=").append(constant).append(",historyNum=")
                .append(historyNum);
        return builder.toString();
    }
}
