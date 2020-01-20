package com.sise.feignClient.main;

import com.sise.feignClient.client.Hello;
import feign.Feign;

/**
 * @program: SpringCloud
 * @description: Lab5 Fegin
 * @author: wxy
 * @create: 2020-01-17 18:22
 **/

public class Main {

    public static void main(String[] args){
        Hello hello = Feign.builder().target(Hello.class, "http://localhost:8080/");
        System.out.println(hello.Hello());
    }

}
