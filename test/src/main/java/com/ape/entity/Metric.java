/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * AngryApe created at 2017-11-21
 */
@Entity
@Table(name = "t_busi_metric_item")
public class Metric implements Serializable {

    @Id
    private Integer id;

    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String alias;
    @Column
    private String py;
    @Column
    private String unit;
    @Column
    private Integer type;
    @Column
    private Integer belong;
    @Column
    private String resolution;
    @Column
    private Integer nature;
    @Column
    private Integer discounted;
    @Column(name = "disalarmed")
    private Integer disAlarmed;
    @Column
    private String owner;
    @Column
    private Integer disabled;
    @Column
    private String memo;
    @Column(name = "origin_code")
    private String code3;

    public Integer getBelong() {
        return belong;
    }

    public void setBelong(Integer belong) {
        this.belong = belong;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public Integer getDiscounted() {
        return discounted;
    }

    public void setDiscounted(Integer discounted) {
        this.discounted = discounted;
    }

    public Integer getDisAlarmed() {
        return disAlarmed;
    }

    public void setDisAlarmed(Integer disAlarmed) {
        this.disAlarmed = disAlarmed;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }
}
