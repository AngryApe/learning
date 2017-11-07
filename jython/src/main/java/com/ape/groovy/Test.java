package com.ape.groovy;

import com.ape.utils.CommonUtils;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;

/**
 * dell created at 2017-11-06
 */
public class Test {

    public static void main(String[] args) {
        ClassLoader parent = Test.class.getClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);

        //找到指定的groovy类
        Class groovyClass = null;
        try {
            Long start = System.currentTimeMillis();
            groovyClass = loader
                    .parseClass(new File(parent.getResource("script/test.groovy").getPath()));
            //将对象实例化并且强制转换为GroovyObject对象
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            //readEmailCodeUrl方法名，null 参数值，没有为null
            for (int i = 0; i < 10000000; i++) {
                Double d1 = Math.random() * 10000;
                Double d2 = Math.random() * 10000;
                Object[] para = new Object[]{d1,d2};
                groovyObject.invokeMethod("testFunction", para);
            }
            CommonUtils.methodCost(start, "Groovy Invoke");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

}
