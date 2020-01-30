package com.sise.firstZuulGateway.config;

import com.sise.firstZuulGateway.filter.ExceptionFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: SpringCloud
 * @description: Lab11
 * @author: wxy
 * @create: 2020-01-28 16:43
 **/

@Configuration
public class FilterConfig {

    @Bean
    public ExceptionFilter exceptionFilter(){
        return new ExceptionFilter();
    }

}
