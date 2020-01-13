package com.sise.invoker.controller;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SpringCloud
 * @description: Invoker
 * @author: wxy
 * @create: 2020-01-11 18:12
 **/

@RestController
@Configuration
public class InvokerController {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    /**
     * @date: 2020/1/12
     * @description: Lab1
     */

    @RequestMapping(value = "router", method = RequestMethod.GET)
    public String router(){
        RestTemplate restTemplate = getRestTemplate();
        String str = restTemplate.getForObject("http://provider/9001", String.class);
        return str;
    }

}
