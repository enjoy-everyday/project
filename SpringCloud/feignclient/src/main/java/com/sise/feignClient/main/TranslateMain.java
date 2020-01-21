package com.sise.feignClient.main;

import com.sise.feignClient.client.Hello;
import com.sise.feignClient.custom.CustomTranslate;
import feign.Feign;

/**
 * @program: SpringCloud
 * @description: Lab6 自定义注解运行
 * @author: wxy
 * @create: 2020-01-20 16:10
 **/

public class TranslateMain {

    public static void main(String[] args){
        Hello hello = Feign.builder().contract(new CustomTranslate()).target(Hello.class, "http://localhost:8080/");
        String result = hello.Hello();
        System.out.println("内容：" + result);
    }

}
