package com.sise.ribbon.service;

import org.springframework.stereotype.Service;

/**
 * @program: SpringCloudLab1
 * @description: HelloWorld
 * @author: wxy
 * @create: 2020-01-11 16:54
 **/

@Service
public class HelloService {
    public String sayHello(){
        return "HelloWorld";
    }
}
