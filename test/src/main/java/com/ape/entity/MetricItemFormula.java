/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 逻辑数据项计算公式
 * table: t_busi_metric_item_formula
 * AngryApe created at 2017-11-24
 */
@Entity
@Table(name = "t_busi_metric_item_formula")
public class MetricItemFormula implements Serializable {

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
     * 数据项来源
     */
    @Column(name = "metric_source")
    private byte source;
    /**
     * 计算入口
     */
    @Column(name = "formula_class")
    private String formulaClass;
    /**
     * 参数个数
     */
    @Column
    private Integer parameters;
    /**
     * 所属方，00000000或空表示中恒云能源，为全平台通用
     */
    @Column
    private String owner;
    /**
     * 备注
     */
    @Column
    private String memo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte getSource() {
        return source;
    }

    public void setSource(byte source) {
        this.source = source;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("code=").append(code).append(",source=").append(source)
                .append(",formulaClass=").append(formulaClass).append(",parameters=")
                .append(parameters).append(",owner=").append(owner).append(",memo=").append(memo);
        return builder.toString();
    }
}
