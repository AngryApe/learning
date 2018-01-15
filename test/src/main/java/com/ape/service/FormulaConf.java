/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.service;

import java.util.HashMap;
import java.util.Map;

/**
 * AngryApe created at 2017-11-27
 */
public class FormulaConf {

    public static final Map<String, String[]> confMap = new HashMap<>();

    private static String statFormulaStr;

    //配置
    static {
        statFormulaStr = "总视在功率0   ApparentPower 总有功功率0 总无功功率0\n"
                + "A相视在功率0 ApparentPower   A相有功功率0 A相无功功率0\n"
                + "B相视在功率0 ApparentPower   B相有功功率0 B相无功功率0\n"
                + "C相视在功率0 ApparentPower   C相有功功率0 C相无功功率0\n"
                + "月峰谷比0   PeakVallyRate   月尖时电量0   月峰时电量0   月谷时电量0\n" + "日最大需量0   MaxDay 当前有功需量0\n"
                + "月最大需量0   MaxMonth   当前有功需量0\n" + "日反向无功电量0   ElectricityDay   反向无功总电能示值0\n"
                + "日正向无功电量0   ElectricityDay   正向无功总电能示值0\n"
                + "日无功电量0   Add    日反向无功电量0    日正向无功电量0\n" + "小时电量0   ElectricityHour 正向有功总电能示值0\n"
                + "日电量0   ElectricityDay   正向有功总电能示值0\n"
                + "日尖时电量0   ElectricityPeakVallyDay    小时电量0\n"
                + "日峰时电量0   ElectricityPeakVallyDay    小时电量0\n"
                + "日平时电量0   ElectricityPeakVallyDay    小时电量0\n"
                + "日谷时电量0   ElectricityPeakVallyDay    小时电量0\n" + "月电量0   SumMonth  日电量0\n"
                + "月尖时电量0   SumMonth   日尖时电量0\n" + "月峰时电量0   SumMonth   日峰时电量0\n"
                + "月平时电量0   SumMonth   日平时电量0\n" + "月谷时电量0   SumMonth   日谷时电量0\n"
                + "实时功率因数0   PowerFactor   总有功功率0 总无功功率0\n"
                + "日平均功率因数0   PowerFactor  日电量0    日无功电量0\n"
                + "三相电压不平衡度0   UnbalanceRate   正序电压0   负序电压0\n"
                + "三相电流不平衡度0   UnbalanceRate   正序电流0   负序电流0\n"
                + "负载率0   LoadRate 总视在功率0  变压器额定容量0\n" + "总有功功率1   GateAggregation    总有功功率0\n"
                + "总无功功率1   GateAggregation    总无功功率0\n" + "日最大负荷1   MaxDay 总有功功率0\n"
                + "实时功率因数1   PowerFactor   总有功功率1  总无功功率1\n" + "需量1   GateAggregation   当前有功需量0\n"
                + "日最大需量1   MaxDay 需量1\n" + "月最大需量1   MaxMonth   需量1\n"
                + "月峰谷比1   PeakVallyRate   月尖时电量1  月峰时电量1  月谷时电量1\n"
                + "日无功电量1   GateAggregation    日无功电量0\n" + "日电量1   GateAggregation  日电量0\n"
                + "日平均功率因数1   PowerFactor  日电量1    日无功电量1\n" + "小时电量1   GateAggregation 小时电量0\n"
                + "日尖时电量1   GateAggregation    日尖时电量0\n" + "日峰时电量1   GateAggregation    日峰时电量0\n"
                + "日平时电量1   GateAggregation    日平时电量0\n" + "日谷时电量1   GateAggregation    日谷时电量0\n"
                + "月电量1   GateAggregation  月电量0\n" + "月尖时电量1   GateAggregation    月尖时电量0\n"
                + "月峰时电量1   GateAggregation    月峰时电量0\n" + "月平时电量1   GateAggregation    月平时电量0\n"
                + "月谷时电量1   GateAggregation    月谷时电量0\n";

        String[] formulas = statFormulaStr.split("\n");
        System.out.println("start to parse config.");
        int size = 0;
        for (String formula : formulas) {
            String[] conf = formula.split("\\s+");
            if (conf.length <= 1) {
                continue;
            }
            size++;
            String[] param = new String[conf.length - 1];
            for (int i = 1; i < conf.length; i++) {
                param[i - 1] = conf[i];
            }
            confMap.put(conf[0], param);
        }
        System.out.println("Finish parse config, size=" + size);
    }

    public static String[] get(String metricName) {
        if (confMap.containsKey(metricName)) {
            return confMap.get(metricName);
        }
        return null;
    }

    public enum Function {
        Add("com.ioe.stat.function.impl.Add"),
        Subtract("com.ioe.stat.function.impl.Subtract"),
        ApparentPower("com.ioe.stat.function.impl.ApparentPower"),
        AvgLoad("com.ioe.stat.function.impl.AvgLoad"),
        LoadRate("com.ioe.stat.function.impl.LoadRate"),
        Increment("com.ioe.stat.function.impl.Increment"),
        PowerFactor("com.ioe.stat.function.impl.PowerFactor"),
        ThreePhaseUnbalance("com.ioe.stat.function.impl.ThreePhaseUnbalance"),
        MinHour("com.ioe.stat.function.impl.MinHour"),
        MinDay("com.ioe.stat.function.impl.MinDay"),
        MinMonth("com.ioe.stat.function.impl.MinMonth"),
        MinYear("com.ioe.stat.function.impl.MinYear"),
        MaxHour("com.ioe.stat.function.impl.MaxHour"),
        MaxDay("com.ioe.stat.function.impl.MaxDay"),
        MaxMonth("com.ioe.stat.function.impl.MaxMonth"),
        MaxYear("com.ioe.stat.function.impl.MaxYear"),
        ElectricityHour("com.ioe.stat.function.impl.ElectricityHour"),
        ElectricityDay("com.ioe.stat.function.impl.ElectricityDay"),
        ElectricityMonth("com.ioe.stat.function.impl.ElectricityMonth"),
        ElectricityYear("com.ioe.stat.function.impl.ElectricityYear"),
        ElectricityPeakVallyHour("com.ioe.stat.function.impl.ElectricityPeakVallyHour"),
        ElectricityPeakVallyDay("com.ioe.stat.function.impl.ElectricityPeakVallyDay"),
        ElectricityPeakVallyMonth("com.ioe.stat.function.impl.ElectricityPeakVallyMonth"),
        ElectricityPeakVallyYear("com.ioe.stat.function.impl.ElectricityPeakVallyYear"),
        StationAggregation("com.ioe.stat.function.impl.StationAggregation");

        Function(String classPath) {
            this.classPath = classPath;
        }

        private String classPath;

        public String getClassPath() {
            return classPath;
        }

        public static boolean contain(String name) {
            boolean exist = false;
            for (Function function : values()) {
                if (function.name().equals(name)) {
                    exist = true;
                    break;
                }
            }
            return exist;
        }
    }

    public static String getClassPath(String key) {
        if (Function.contain(key)) {
            return Function.valueOf(key).getClassPath();
        }
        return "";
    }

    public static void main(String[] args) {
        //        String[] formulas = statFormulaStr.split("\n");
        //        System.out.println("size:" + formulas.length);
        int radix = 1;
        long start = System.currentTimeMillis();
        int loops = 10000000;
        for (int i = 0; i < loops; i++) {
            long nanoTime = System.nanoTime();
            Long.toString(nanoTime, radix); //36:2214,
//            Long.toHexString(nanoTime);820
        }
        System.out.println(
                loops + " times convert cost " + (System.currentTimeMillis() - start) + " ms");

        //        System.out.println(Long.MAX_VALUE);
        //        System.out.println(nanoTime + " to " + radix + " hex is " + Long.toString(nanoTime, radix));
    }
}
