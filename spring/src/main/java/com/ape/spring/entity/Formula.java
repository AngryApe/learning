package com.ape.spring.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Formula implements Serializable {

    private String formula;

    private String[] variableList;

    private String pointId;

    private String stationId;

    private String formulaId;

    private String statisticalType;

    private long statSecond;

    private String type;

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String[] getVariableList() {
        return variableList;
    }

    public void setVariableList(String[] variableList) {
        this.variableList = variableList;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public String getStatisticalType() {
        return statisticalType;
    }

    public void setStatisticalType(String statisticalType) {
        this.statisticalType = statisticalType;
    }

    public long getStatSecond() {
        return statSecond;
    }

    public void setStatSecond(long statSecond) {
        this.statSecond = statSecond;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.formulaId + "" + this.formula + "" + this.stationId + "" + this.pointId + ""
                + Arrays.toString(this.variableList);
    }
}
