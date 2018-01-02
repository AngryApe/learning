package com.ape.utils;

import java.lang.management.ManagementFactory;
import java.util.*;

/**
 * AngryApe created at 2017/10/12
 */
public class CommonUtils {

    /*--------------------List转字符串---------------------*/
    public static String listToString(List<String> list, String separator, String open,
            String close) {
        if (list == null || list.size() < 1)
            return "";
        StringBuilder sb = new StringBuilder("");
        int index = 0;
        for (String str : list) {
            sb.append(open).append(str).append(close);
            if (index + 1 < list.size())
                sb.append(separator);
            index++;
        }
        return sb.toString();
    }

    public static String listToString(List<String> list) {
        return listToString(list, ",", "", "");
    }

    public static String listToSqlString(List<String> list) {
        return listToString(list, ",", "'", "'");
    }
    /*--------------------Map 操作---------------------*/

    /**
     * 向 value 为List<String> 的map中添加值
     */
    public static <K, V> void addMapList(Map<K, List<V>> map, K key, V value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(value);
    }

    /*---------------------非空判断-----------------------*/
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str);
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }

    public static <T> List<T> arrayListNullToEmpty(List<T> collection) {
        return isEmpty(collection) ? new ArrayList<T>() : collection;
    }

    /*---------------------方法内部性能调试----------------*/

    /**
     * 需要将调试信息统一输出时使用
     */
    public static Long methodCost(Long start, String methodName, StringBuilder sb) {
        Long tEnd = System.currentTimeMillis();
        sb.append(methodName + " execute cost time " + (tEnd - start) + " ms\n");
        return tEnd;
    }

    public static Long methodCost(Long start, String methodName) {
        Long tEnd = System.currentTimeMillis();
        System.out.println(methodName + " execute cost time " + (tEnd - start) + " ms\n");
        return tEnd;
    }

    /*---------------------系统相关----------------------*/

    /**
     * 判断程序是否是调试状态
     * 推荐在程序启动时，将该值设为常量（static final）
     */
    public static boolean isDebug() {
        List<String> args = ManagementFactory.getRuntimeMXBean().getInputArguments();
        boolean isDebug = false;
        for (String arg : args) {
            if (arg.startsWith("-Xrunjdwp") || arg.startsWith("-agentlib:jdwp")) {
                isDebug = true;
                break;
            }
        }
        return isDebug;
    }

    public static void main(String[] args) {
        String str = "日最大需量   Max 当前有功需量";
        String[] splitStr = str.split("\\s+");
        for (String s : splitStr) {
            System.out.println(s);
        }
        System.out.println(splitStr);
    }
}
