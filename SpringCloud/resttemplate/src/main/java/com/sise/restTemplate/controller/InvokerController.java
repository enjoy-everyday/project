package com.sise.restTemplate.controller;

import com.sise.restTemplate.template.MyLoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SpringCloud
 * @description: RestTemplate
 * @author: wxy
 * @create: 2020-01-16 20:51
 **/

@RestController
@Configuration
public class InvokerController {

    @Bean
    @MyLoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping(value = "/router", method = RequestMethod.GET)
    public String router(){
        RestTemplate restTemplate = getRestTemplate();
        String json = restTemplate.getForObject("http://my-server/hello", String.class);
        return json;
    }

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello(){
        return "Hello";
    }

}
