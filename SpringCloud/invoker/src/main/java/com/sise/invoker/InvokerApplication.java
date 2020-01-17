package com.sise.invoker;

import com.netflix.config.ConfigurationManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.IOException;

@SpringBootApplication
@EnableDiscoveryClient
public class InvokerApplication {

    public static void main(String[] args) throws IOException {
        ConfigurationManager.loadPropertiesFromResources("application.yml");
        SpringApplication.run(InvokerApplication.class, args);
    }

}
