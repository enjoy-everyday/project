package com.sise.secondRabbitConsumer;

import com.sise.secondRabbitConsumer.service.ReceiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@SpringBootApplication
@EnableBinding(value = {ReceiveService.class})
public class SecondRabbitConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondRabbitConsumerApplication.class, args);
    }

    @StreamListener("myInput")
    public void receive(byte[] message){
        System.out.println("groupB Second Consumer 接收消息：" + new String(message));
    }

}
