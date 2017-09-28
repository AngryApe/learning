package com.ape.dubbo.service;

import com.ape.dubbo.interfaces.DubboService;
import org.springframework.stereotype.Service;

/**
 * AngryApe created at 2017/9/28
 */
@Service("dubboService")
public class DubboServiceImpl implements DubboService {

    public String sayHello(String name) {
        return "Hello " + name;
    }
}
