package com.sise.zuulGateway.custom;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: SpringCloud
 * @description: Lab11
 * @author: wxy
 * @create: 2020-01-28 11:31
 **/

@Configuration
public class CustomConfig {

    @Bean
    public PatternServiceRouteMapper patternServiceRouteMapper(){
        return new PatternServiceRouteMapper("(?<module>.+)-(service)", "${module}/**");
    }

}
