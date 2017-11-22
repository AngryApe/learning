package com.ape.jython;

import org.python.core.PyFloat;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * AngryApe created at 2017-10-31
 */
public class PythonInvoker{

    private Map<String, PyFunction> pyFunctions;

    private PythonInterpreter interpreter;

    public static PyConfiguration configuration = new PyConfiguration();

    public PythonInvoker() {
        init();
        refreshPyFunctions();
    }

    public void init() {
        pyFunctions = new HashMap<>();
        Properties props = new Properties();
//        props.put("python.console.encoding", "UTF-8");
//        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");

        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        interpreter = new PythonInterpreter();
        interpreter.exec("import sys");
        interpreter.exec("print sys.path");

        String path = PythonInvoker.class.getClassLoader().getResource("script/main.py").getPath();
//        String path = "D:\\workspace\\learning\\jython\\src\\main\\resources\\script\\main.py";
        interpreter.execfile(path);
    }

    private void reload() {
        interpreter.close();
        init();
        refreshPyFunctions();
    }

    public void refreshPyFunctions() {
        for (String key : configuration.keySet()) {
            PyFunction function = interpreter.get(configuration.get(key), PyFunction.class);
            pyFunctions.put(configuration.get(key), function);
        }
    }

    public PyObject call(String key, String... parameters) {
        if (parameters == null || parameters.length < 1)
            return null;
        if (key.equals("reload")) {
            this.reload();
            return null;
        }
        PyObject[] pyObj = new PyObject[parameters.length];
        int index = 0;
        //        for (double parameter : parameters) {
        //            pyObj[index++] = new PyFloat(parameter);
        //        }
        PyFunction pyFunction = pyFunctions.get("execute");
        return pyFunction.__call__(new PyString(key));
    }

    public static void main(String[] args) {
        PythonInvoker invoker = new PythonInvoker();
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        while (run) {
            System.out.println("========1.开方根 2.reload 3.exit========");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    PyObject res = invoker.call("testFunction", "1");
                    System.out.println((PyFloat) res);
                    break;
                case "2":
                    //                    System.out.println("请输入版本号：");
                    //                    String version = scanner.nextLine();
                    invoker.call("reload", "1");
                    System.out.println("Reload complete.\n");
                    break;
                case "3":
                    run = false;
                    break;
                default:
                    System.out.println("输入错误，请重新选择...");
            }
        }
    }

}
