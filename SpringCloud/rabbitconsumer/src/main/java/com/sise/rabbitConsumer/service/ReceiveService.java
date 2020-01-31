package com.sise.rabbitConsumer.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @program: SpringCloud
 * @description: Lab12
 * @author: wxy
 * @create: 2020-01-30 16:54
 **/

public interface ReceiveService {

    @Input("myInput")
    SubscribableChannel myInput();

}
