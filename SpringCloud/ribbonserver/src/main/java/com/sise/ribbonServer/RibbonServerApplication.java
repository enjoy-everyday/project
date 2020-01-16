package com.sise.ribbonServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Scanner;

@SpringBootApplication
public class RibbonServerApplication {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String port = scanner.nextLine();
        new SpringApplicationBuilder(RibbonServerApplication.class).properties("spring.config.location=classpath:/application.yml").properties("server.port=" + port).run(args);

    }

}
