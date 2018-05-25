/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.function;

/**
 *
 * AngryApe created at 2017-12-04
 */
public class Add extends Function {

    @Override
    public Double execute() {
        return Math.sqrt(Math.pow(Math.random()*100,2)+Math.pow(Math.random()*100,2));
    }
}
