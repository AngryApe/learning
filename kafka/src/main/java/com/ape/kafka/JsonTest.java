/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.kafka;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @auther qiys@hzzh.com
 * @date 2018-03-29
 */
public class JsonTest {

    static class A {

        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class B {

        public int age;

        public A a;
    }

    public static void main(String[] args) {
        A a = new A();
        a.name = "test";
        B b1 = new B();
        B b2 = new B();
        b1.age = 10;
        b1.a = a;
        b2.a = a;
        b2.age = 20;

        System.out.println(JSONObject.toJSONString(Arrays.asList(b1, b2), SerializerFeature.DisableCircularReferenceDetect));

        HashMap<String, B> map = new HashMap<>();
        map.put("1",b1);
        map.put("2",b2);
        System.out.println(JSONObject.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect));
    }

}
