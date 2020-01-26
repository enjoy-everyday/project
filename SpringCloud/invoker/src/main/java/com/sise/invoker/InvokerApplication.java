package com.sise.invoker;

import com.netflix.config.ConfigurationManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@ServletComponentScan
public class InvokerApplication {

    /**
     * @date: 2020/1/16
     * @description: Lab4
     */

//    public static void main(String[] args) throws IOException {
//        ConfigurationManager.loadPropertiesFromResources("application.yml");
//        SpringApplication.run(InvokerApplication.class, args);
//    }

    /**
     * @date: 2020/1/20
     * @description: Lab6
     */

//    public static void main(String[] args) {
//        SpringApplication.run(InvokerApplication.class, args);
//    }

    /**
     * @date: 2020/1/24
     * @description: Lab9
     */

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(InvokerApplication.class, args);
    }

}
