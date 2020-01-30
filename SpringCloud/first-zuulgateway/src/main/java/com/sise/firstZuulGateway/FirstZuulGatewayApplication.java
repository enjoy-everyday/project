package com.sise.firstZuulGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class FirstZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstZuulGatewayApplication.class, args);
    }

}
