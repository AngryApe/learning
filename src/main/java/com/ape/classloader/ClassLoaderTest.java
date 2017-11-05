package com.ape.classloader;

import com.ape.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * AngryApe created at 2017-11-03
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Invoker invoker = new Invoker();
        Double max = 100.0;
        Double min = 10.0;
        List<Double> parameter = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            parameter.add(Math.random() * 200);
        }
        while (true) {
            System.out.println("=+===================================+=");
//            System.out.println("-+-------Please enter the input.-----+-");
//            System.out.println("-+-------1.load class file 1---------+-");
//            System.out.println("-+-------2.load class file 2---------+-");
//            System.out.println("-+-------0.exit----------------------+-");
//            System.out.println("-+-----------------------------------+-");
            String in = sc.next();
            if ("1".equals(in) || "2".equals(in)) {
                System.out.println("**********************************");
                System.out.println("Do load().");
                invoker.reload(Integer.valueOf(in));
            } else if ("0".equals(in)) {
                System.out.println("Bye.");
                System.exit(0);
            } else if ("3".equals(in)) {
                Long start = System.currentTimeMillis();
                for (int i = 0; i < 100000000; i++) {
                    invoker.exec("max", max, parameter);
                    invoker.exec("min", min, parameter);
                }
                CommonUtils.methodCost(start,"热加载调用");
            } else {
                System.out.println("Error Input.");
                continue;
            }
        }
    }

}
