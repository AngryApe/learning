package com.ape.jython;

import org.python.core.PyFloat;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * AngryApe created at 2017-10-31
 */
public class PythonInvoker {

    private Map<String, PyFunction> pyFunctions;

    private PythonInterpreter interpreter;

    public static PyConfiguration configuration = new PyConfiguration();

    public PythonInvoker() {
        init();
        refreshPyFunctions();
    }

    private void init() {
        pyFunctions = new HashMap<>();
        Properties props = new Properties();
        //        props.put("python.home", "F:\\spring-app\\src\\main\\webapp\\WEB-INF\\lib");
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");

        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        interpreter = new PythonInterpreter();
        interpreter.exec("import sys");
        interpreter.exec("print sys.path");
        try {
            interpreter.execfile(PythonInvoker.class.getClassLoader().getResource("script/test.py")
                    .openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshPyFunctions() {
        for (String key : configuration.keySet()) {
            PyFunction function = interpreter.get(configuration.get(key), PyFunction.class);
            pyFunctions.put(configuration.get(key), function);
        }
    }

    public PyObject call(String key, double... parameters) {
        if (parameters == null || parameters.length < 1)
            return null;
        PyObject[] pyObj = new PyObject[parameters.length];
        int index = 0;
        for (double parameter : parameters) {
            pyObj[index++] = new PyFloat(parameter);
        }
        PyFunction pyFunction = pyFunctions.get(key);
        return pyFunction.__call__(pyObj);
    }

    public static void main(String[] args) {
        Properties props = new Properties();
        //        props.put("python.home", "F:\\spring-app\\src\\main\\webapp\\WEB-INF\\lib");
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        PythonInterpreter interpreter = new PythonInterpreter();

    }

}
