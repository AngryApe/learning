package com.ape.classloader;

import com.ape.utils.CommonUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AngryApe created at 2017-11-03
 */
public class Invoker {

    //    public void loadClass(String )

    public static Object exec(String methodName, Map<String, Object> params) {
        if (CommonUtils.isEmpty(methodName) || params == null || params.isEmpty()) {
            return null;
        }
        try {
            Method method = ClassLoaderLocal.methodMap.get(methodName);
            return method.invoke(null, params);
        } catch (Exception e) {

        }
        return null;
    }

    public static void reload(){
        String path1 = "";
        String path2 = "";


    }

    private static class ClassLoaderLocal extends ClassLoader {

        public static Map<String, Method> methodMap = new ConcurrentHashMap<>();

        public void loadClass(byte[] bytes) {

        }

    }

}
