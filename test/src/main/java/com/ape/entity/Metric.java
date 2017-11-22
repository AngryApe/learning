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
@Table(name = "t_busi_metric_item_logic")
public class Metric implements Serializable {

    @Id
    private int id;

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
    private String resolution;
    @Column
    private int nature;
    @Column
    private int discounted;
    @Column
    private int disalarmed;
    @Column
    private String owner;
    @Column
    private int disabled;
    @Column
    private String memo;
    @Column(name = "code_3")
    private String code3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getNature() {
        return nature;
    }

    public void setNature(int nature) {
        this.nature = nature;
    }

    public int getDiscounted() {
        return discounted;
    }

    public void setDiscounted(int discounted) {
        this.discounted = discounted;
    }

    public int getDisalarmed() {
        return disalarmed;
    }

    public void setDisalarmed(int disalarmed) {
        this.disalarmed = disalarmed;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
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
