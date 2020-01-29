package com.sise.zuulGateway.config;

import com.sise.zuulGateway.filter.ExceptionFilter;
import com.sise.zuulGateway.filter.RestTemplateFilter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SpringCloud
 * @description: Lab11
 * @author: wxy
 * @create: 2020-01-28 16:43
 **/

@Configuration
public class FilterConfig {

    @Bean
    public RestTemplateFilter restTemplateFilter(RestTemplate restTemplate){
        return new RestTemplateFilter(restTemplate);        //注入RestTemplate
    }

    @Bean
    public ExceptionFilter exceptionFilter(){
        return new ExceptionFilter();
    }

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
