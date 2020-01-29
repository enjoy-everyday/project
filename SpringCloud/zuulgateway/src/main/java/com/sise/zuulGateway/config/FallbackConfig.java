package com.sise.zuulGateway.config;

import com.sise.zuulGateway.custom.CustomFallbackProvider;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: SpringCloud
 * @description: Lab11
 * @author: wxy
 * @create: 2020-01-28 15:45
 **/

@Configuration
public class FallbackConfig {

    @Bean
    public FallbackProvider saleFallbackProvider(){
        return new CustomFallbackProvider();
    }

}
