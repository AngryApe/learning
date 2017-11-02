package com.ape.jython;

import java.util.HashMap;

/**
 * AngryApe created at 2017-10-31
 * 配置模拟
 */
public class PyConfiguration extends HashMap<String,String>{

    public PyConfiguration() {
        super();
        this.put("adder","adder");
        this.put("testFunction","testFunction");
        this.put("testInstance","testInstance");
        this.put("setVar","setVar");
    }
}
