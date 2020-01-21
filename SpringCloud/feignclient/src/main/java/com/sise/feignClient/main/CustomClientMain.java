package com.sise.feignClient.main;

import com.sise.feignClient.client.Hello;
import com.sise.feignClient.custom.CustomFeignClient;
import feign.Feign;

/**
 * @program: SpringCloud
 * @description: Lab6 自定义客户端运行
 * @author: wxy
 * @create: 2020-01-20 15:23
 **/

public class CustomClientMain {

    public static void main(String[] args){
        Hello hello = Feign.builder().client(new CustomFeignClient()).target(Hello.class, "http://localhost:8080/");
        System.out.println("内容：" + hello.Hello());
    }

}
