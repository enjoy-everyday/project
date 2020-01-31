package com.sise.rabbitProvider.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * @program: SpringCloud
 * @description: Lab12
 * @author: wxy
 * @create: 2020-01-30 16:05
 **/

public interface SendService {

    @Output("myInput")      //创建myInput的消息通道
    SubscribableChannel sendOrder();

}
