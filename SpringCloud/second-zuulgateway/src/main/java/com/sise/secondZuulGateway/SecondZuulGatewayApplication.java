package com.sise.secondZuulGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SecondZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondZuulGatewayApplication.class, args);
    }

}
