package com.sise.feignClient.main;

import com.sise.feignClient.client.RS;
import feign.Feign;
import feign.jaxrs.JAXRSContract;

/**
 * @program: SpringCloud
 * @description: Lab6 第三方注解
 * @author: wxy
 * @create: 2020-01-20 15:33
 **/

public class RSMain {

    public static void main(String[] args){
        RS rs = Feign.builder().contract(new JAXRSContract()).target(RS.class, "http://localhost:8080/");
        String result = rs.Hello();
        System.out.println("内容：" + result);
    }

}
