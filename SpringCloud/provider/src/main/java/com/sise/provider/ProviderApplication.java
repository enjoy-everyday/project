package com.sise.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Scanner;

@SpringBootApplication
@EnableEurekaClient
public class ProviderApplication {


    /**
     * @date: 2020/1/12
     * @description: Lab1
     */

//    public static void main(String[] args) {
//        SpringApplication.run(ProviderApplication.class, args);
//    }

    /**
     * @date: 2020/1/13
     * @description: Lab2-1
     */

//    public static void main(String[] args) {
//        @SuppressWarnings("resource")
//        Scanner scanner = new Scanner(System.in);
//        String port = scanner.nextLine();    //从控制台读取端口号
//        System.out.println(port);
//        new SpringApplicationBuilder(ProviderApplication.class).properties("spring.config.location=classpath:/application.yml").properties("server.port=" + port).run(args);
//    }

    /**
     * @date: 2020/1/14
     * @description: Lab2-2
     */

//    public static void main(String[] args) {
//        SpringApplication.run(ProviderApplication.class, args);
//    }


    /**
     * @date: 2020/1/16
     * @description: Lab4、Lab4
     */
//
//    @SuppressWarnings("resource")
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String port = scanner.nextLine();    //从控制台读取端口号
//        System.out.println(port);
//        new SpringApplicationBuilder(ProviderApplication.class).properties("spring.config.location=classpath:/application.yml").properties("server.port=" + port).run(args);
//    }

    /**
     * @date: 2020/1/24
     * @description: Lab9
     */

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
