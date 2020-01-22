package com.sise.invoker.custom;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: SpringCloud
 * @description: Lab6 实验2
 * @author: wxy
 * @create: 2020-01-20 22:55
 **/

@Configuration
public class MyConfig {

    @Bean
    public Contract feignContract(){
        return new MyContract();
    }

}
