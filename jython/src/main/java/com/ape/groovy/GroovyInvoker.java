package com.ape.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;

/**
 * dell created at 2017-11-06
 */
public class GroovyInvoker {

    GroovyObject groovyObject;

    public GroovyInvoker() {
        init();
    }

    private void init() {
        ClassLoader parent = GroovyInvoker.class.getClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);
        //找到指定的groovy类
        Class groovyClass = null;
        try {
            groovyClass = loader
                    .parseClass(new File(parent.getResource("script/test.groovy").getPath()));
            //将对象实例化并且强制转换为GroovyObject对象
            groovyObject = (GroovyObject) groovyClass.newInstance();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public Double invoke(String methodName, Object[] para) {
        //readEmailCodeUrl方法名，null 参数值，没有为null
        return (Double) groovyObject.invokeMethod(methodName, para);
    }

}
