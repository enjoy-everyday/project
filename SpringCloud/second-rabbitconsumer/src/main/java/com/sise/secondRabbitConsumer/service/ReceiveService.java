package com.sise.secondRabbitConsumer.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @program: SpringCloud
 * @description: Lab12 实验
 * @author: wxy
 * @create: 2020-01-30 17:20
 **/

public interface ReceiveService {

    @Input("myInput")
    SubscribableChannel myInput();

}
