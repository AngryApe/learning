/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */

import com.alibaba.fastjson.JSON;
import com.ape.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @auther qiys@hzzh.com
 * @date 2018-05-07
 */
public class FastJsonPerf {

    private final static int MB = 1024 * 1024;

    public static void main(String[] args) {
        int num = 150000;
        int offsetStart = 1525600000;
        System.out.println("One map test:");
        oneMap(num, offsetStart);
        System.out.println("Objects test:");
        objects(num, offsetStart);
        try {
            Thread.sleep(1000 * 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void oneMap(int dataSize, int offsetStart) {
        Long start = System.currentTimeMillis();
        HashMap<String, String> data = new HashMap<>();
        for (int i = 0; i < dataSize; i++) {
            data.put(String.valueOf(offsetStart + i),
                    String.valueOf(CommonUtils.rangeRandom(10, 500)));
        }
        start = CommonUtils.methodCost(start, "generate data");

        String json = JSON.toJSONString(data);

        start = CommonUtils.methodCost(start, "serialize to json");
        System.out.println("data size:" + json.getBytes().length * 1.0 / MB + "MB\n");
    }

    public static void objects(int dataSize, int offsetStart) {
        List<HashMap> result = new ArrayList<>();
        List<HashMap<String, String>> data = new ArrayList<>();
        Long start = System.currentTimeMillis();
        HashMap<String, Object> point = null;
        for (int i = 0; i < dataSize; i++) {
            if (i % 50000 == 0) {
                if (point != null) {
                    point.put("data", data);
                    data = new ArrayList<>();
                    result.add(point);
                }
                point = new HashMap<>();
                point.put("point", i);
            }
            HashMap<String, String> obj = new HashMap<>();
            obj.put("t", String.valueOf(offsetStart + i));
            obj.put("v", String.valueOf(CommonUtils.rangeRandom(10, 500)));
            data.add(obj);
        }
        point.put("data", data);
        result.add(point);
        start = CommonUtils.methodCost(start, "generate data");

        String json = JSON.toJSONString(result);

        start = CommonUtils.methodCost(start, "serialize to json");

        System.out.println("data size:" + json.getBytes().length * 1.0 / MB + "MB\n");
    }
}
