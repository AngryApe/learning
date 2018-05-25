package test; /**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */

import java.lang.reflect.Method;

/**
 * @auther qiys@hzzh.com
 * @date 2018-05-08
 */
public class ReflectionTest {

    public static void main(String[] args) {
        Class clazz = ReflectionTest.class;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println("------------------------------------");
            System.out.println(method.getName());
            System.out.println(method.getDeclaringClass().getName());
            System.out.println(method.toString());
            System.out.println(method.toGenericString());
        }
    }

    public void testMethod(int a, String b){

    }
}
