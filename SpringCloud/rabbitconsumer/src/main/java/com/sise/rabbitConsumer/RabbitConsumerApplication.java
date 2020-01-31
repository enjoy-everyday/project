package com.sise.rabbitConsumer;

import com.sise.rabbitConsumer.service.ReceiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@SpringBootApplication
@EnableBinding(ReceiveService.class)
public class RabbitConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitConsumerApplication.class, args);
    }

    @StreamListener("myInput")      //声明订阅myInput
    public void receive(byte[] message){
        System.out.println("消息：" + new String(message));
    }

}
