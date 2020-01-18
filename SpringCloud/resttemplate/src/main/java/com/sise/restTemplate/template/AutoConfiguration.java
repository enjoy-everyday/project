package com.sise.restTemplate.template;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: SpringCloud
 * @description: RestTemplate
 * @author: wxy
 * @create: 2020-01-16 20:22
 **/

@Configuration
public class AutoConfiguration {

    @Autowired(required = false)
    @MyLoadBalanced
    private List<RestTemplate> templates = Collections.emptyList();

    @Bean
    public SmartInitializingSingleton myLoadBalancedRestTemplateInitializer(){
        System.out.println("********容器初始化时创建此Bean********");
        return new SmartInitializingSingleton() {
            @Override
            public void afterSingletonsInstantiated() {
                for (RestTemplate restTemplate : templates){
                    Interceptor interceptor = new Interceptor();
                    List list = new ArrayList(restTemplate.getInterceptors());
                    list.add(interceptor);
                    restTemplate.setInterceptors(list);
                }
            }
        };
    }



}
