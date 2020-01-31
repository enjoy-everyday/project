package com.sis.firstRabbitConsumer;

import com.sis.firstRabbitConsumer.service.ReceiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@SpringBootApplication
@EnableBinding(value = {ReceiveService.class})
public class FirstRabbitConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstRabbitConsumerApplication.class, args);
    }

    @StreamListener("myInput")
    public void receive(byte[] message){
        System.out.println("groupB First Consumer 接收消息：" + new String(message));
    }

}
