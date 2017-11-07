package com.ape.jython;

import com.ape.groovy.GroovyInvoker;
import com.ape.utils.CommonUtils;

/**
 * AngryApe created at 2017-10-31
 */
public class PressureTest {

    private static int CYCLE_TIMES = 10_000_000;

    public static void main(String[] args) {
//        System.out.println(pythonInvoker.call("testInstance"));
//        pythonInvoker.call("setVar",3);
//        System.out.println(pythonInvoker.call("testInstance"));
        new PressureTest().test1();

    }

    public void test1(){
        System.out.println("Start testing...");
        PythonInvoker pythonInvoker = new PythonInvoker();
        GroovyInvoker groovyInvoker = new GroovyInvoker();
        Long start = System.currentTimeMillis();
        double temp = 0;
        for (int i = 0; i < CYCLE_TIMES; i++) {
            Double d1 = Math.random() * 10000;
            Double d2 = Math.random() * 10000;
            temp = testFunction(d1, d2);
        }
        System.out.println(temp);
        start = CommonUtils.methodCost(start, "Java Invoke");

        temp = 0;
        for (int i = 0; i < CYCLE_TIMES; i++) {
            Double d1 = Math.random() * 10000;
            Double d2 = Math.random() * 10000;
            Object[] para = new Object[]{d1,d2};
            String result = groovyInvoker.invoke("testFunction", para).toString();
            temp = Double.valueOf(result);
        }
        System.out.println(temp);
        start = CommonUtils.methodCost(start, "Groovy Invoke");

        temp = 0;
        for (int i = 0; i < CYCLE_TIMES; i++) {
            Double d1 = Math.random() * 10000;
            Double d2 = Math.random() * 10000;
            String result = pythonInvoker.call("testFunction", d1, d2).toString();
            temp = Double.valueOf(result);
        }
        System.out.println(temp);
        CommonUtils.methodCost(start, "Jython Invoke");


    }

    private static double add(double a, double b) {
        return a + b;
    }

    private static double testFunction(double a, double b) {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

}
