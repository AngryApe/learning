package com.ape.dubbo.controller;

import com.ape.dubbo.interfaces.DubboService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * AngryApe created at 2017/9/28
 */
@RestController("/hello")
public class DubboController {

    @Resource
    private DubboService dubboService;

}
