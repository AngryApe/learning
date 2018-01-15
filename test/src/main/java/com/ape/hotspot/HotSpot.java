/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.hotspot;

import com.ape.utils.CommonUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * AngryApe created at 2017-12-04
 */
public class HotSpot {

    private static Map<String, Object> functionMap = new HashMap<>();

    public static void main(String[] args) {
        reload();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("--+----------------------------+--");
            System.out.println("--+---1.Reload jar-------------+--");
            System.out.println("--+---2.Execute function-------+--");
            System.out.println("--+---3.Exit-------------------+--");
            System.out.println("--+----------------------------+--");
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    reload();
                    break;
                case "2":
                    Object object = functionMap.get("Add");
                    try {
                        Method exeMethod = object.getClass().getMethod("execute",null);
                        Double result = 0.0;
                        long start = System.currentTimeMillis();
                        for (int i = 0; i < 1_0000_0000; i++) {
                            result = (Double) exeMethod.invoke(object);
                        }
                        CommonUtils.methodCost(start,"1_0000_0000 times ");
                        System.out.println(result);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    run = false;
                    break;
                default:
                    System.out.println("Input error, please input 1 or 2 or 3.");
            }
        }
    }

    private static void reload() {
        String jarPath = "D:\\workspace\\learning\\functions\\target\\functions-1.0-SNAPSHOT.jar";
        List<Class<?>> classes = new ArrayList<>();
        boolean recursive = true;
        try {
            URL[] urls = new URL[] {new File(jarPath).toURI().toURL()};
            URLClassLoader classLoader = new URLClassLoader(urls,
                    Thread.currentThread().getContextClassLoader());
            String packageName = "com.ape.function";
            String packageDirName = packageName.replace('.', '/');
            Class clz = classLoader.loadClass("com.ape.function.Add");
            functionMap.put("Add",clz.newInstance());
        } catch (Exception e){

        }
    }
}

