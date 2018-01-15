/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.test;

import junit.framework.TestCase;
import powercloud.IoTPlatform.coderGenerator.CoderGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther qiys@hzzh.com
 * @date 2017-12-20
 */
public class LockTest extends TestCase {

    public void testPrint() {
        List<String> codes = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                int i = 0;
                while (i++ < 10) {
//                    System.out.println(generateLogicalDeviceCode(codes,'1'));
                    System.out.println(CoderGenerator.getLogicalDeiviceCode('1'));
                }
            }).start();
        }
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private String generateLogicalDeviceCode(List<String> codes, char nodeId) {
        boolean flag = true;
        String code = "";
        while (flag) {
            String generatedCode = CoderGenerator.getLogicalDeiviceCode(nodeId);
            if (!codes.contains(generatedCode)) {
                codes.add(generatedCode);
                code = generatedCode;
                flag = false;
            }
        }
        return code;
    }
}
