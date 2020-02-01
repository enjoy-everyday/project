package com.sise.rabbitProvider;

import com.sise.rabbitProvider.service.SendService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(SendService.class)       //容器启动时，会自动绑定SendService接口中定义的通道
@EnableFeignClients
public class RabbitProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitProviderApplication.class, args);
    }

}
