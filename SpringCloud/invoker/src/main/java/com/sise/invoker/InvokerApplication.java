package com.sise.invoker;

import com.netflix.config.ConfigurationManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
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

    public static void main(String[] args) {
        SpringApplication.run(InvokerApplication.class, args);
    }

}
