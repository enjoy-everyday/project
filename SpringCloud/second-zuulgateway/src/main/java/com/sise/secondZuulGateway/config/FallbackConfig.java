package com.sise.secondZuulGateway.config;

import com.sise.secondZuulGateway.custom.CustomFallbackProvider;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: SpringCloud
 * @description: Lab11 实验
 * @author: wxy
 * @create: 2020-01-28 21:15
 **/

@Configuration
public class FallbackConfig {

    @Bean
    public FallbackProvider saleFallbackProvider(){
        return new CustomFallbackProvider();
    }

}
