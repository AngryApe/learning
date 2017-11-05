package com.ape.classloader;

import com.ape.utils.CommonUtils;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注意事项：
 * 1. class 文件必须是同一版本的JDK编译出来的
 * 2. 要实现动态加载，必须通过不同的ClassLoader实例去实现，用同一个会取缓存导致不生效
 * <p>
 * AngryApe created at 2017-11-03
 */
public class Invoker {

    //    public void loadClass(String )
    private final Map<String, Method> methodMap = new ConcurrentHashMap<>();
    private final String CLASS_NAME = "com.ape.classloader.Arithmetic";

    public Object exec(String methodName, Double p1, List<Double> p2) {
        if (CommonUtils.isEmpty(methodName)) {
            return null;
        }
        try {
            Method method = methodMap.get(methodName);
            if (method != null) {
                if (CommonUtils.isDebug()) {
                    System.out.println("method [" + methodName + "] invoked.");
                }
                return method.invoke(null, p1, p2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void reload(int i) {
        String path1 = "F:\\temp\\1\\Arithmetic.class";
        String path2 = "F:\\temp\\2\\Arithmetic.class";
        String path = "";
        switch (i) {
            case 1:
                path = path1;
                break;
            case 2:
                path = path2;
                break;
            default:
        }
        methodMap.clear();
        String classPath = Invoker.class.getResource("/").getPath();
        new FileOperation().copy(path, classPath + CLASS_NAME.replace(".", "/") + ".class");
        ClassLoader classLoader = new HotSwap(null);
        try {
            Class clazz = classLoader.loadClass(CLASS_NAME);
            if (clazz != null) {
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (CLASS_NAME.equals(method.getDeclaringClass().getName())) {
                        methodMap.put(method.getName(), method);
                        if (CommonUtils.isDebug()) {
                            System.out.println(method.getName());
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class FileOperation {
        public void copy(String filePath, String toPath) {
            try {
                FileInputStream fis = new FileInputStream(new File(filePath));
                FileOutputStream fos = new FileOutputStream(new File(toPath));
                byte[] buf = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buf)) > 0) {
                    fos.write(buf, 0, bytesRead);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class HotSwap extends ClassLoader {

        private String basedir; // 需要该类加载器直接加载的类文件的基目录
        private HashSet dynaclazns; // 需要由该类加载器直接加载的类名


        public HotSwap(String[] clazns) {
            super(null); // 指定父类加载器为 null
            this.basedir = ClassLoader.class.getResource("/").getFile();
            dynaclazns = new HashSet();
            dynaclazns.add(CLASS_NAME);
            loadClassByMe(dynaclazns);
        }

        private void loadClassByMe(Set<String> clazns) {
            if (CommonUtils.isEmpty(clazns))
                return;
            clazns.forEach(clazz -> {
                loadDirectly(clazz);
                dynaclazns.add(clazz);
            });
        }

        private Class loadDirectly(String name) {
            Class cls = null;
            StringBuffer sb = new StringBuffer(basedir);
            String classname = name.replace('.', File.separatorChar) + ".class";
            sb.append(File.separator + classname);
            File classF = new File(sb.toString());
            try {
                cls = instantiateClass(name, new FileInputStream(classF),
                        classF.length());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return cls;
        }

        private Class instantiateClass(String name, InputStream fin, long len) {
            byte[] raw = new byte[(int) len];
            try {
                fin.read(raw);
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return defineClass(name, raw, 0, raw.length);
        }

        protected Class loadClass(String name, boolean resolve)
                throws ClassNotFoundException {
            Class cls = null;
            cls = findLoadedClass(name);
            if (!this.dynaclazns.contains(name) && cls == null)
                cls = getSystemClassLoader().loadClass(name);
            if (cls == null)
                throw new ClassNotFoundException(name);
            if (resolve)
                resolveClass(cls);
            return cls;
        }

    }
}
