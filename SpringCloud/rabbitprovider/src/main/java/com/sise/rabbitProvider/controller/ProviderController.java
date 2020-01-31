package com.sise.rabbitProvider.controller;

import com.sise.rabbitProvider.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloud
 * @description: Lab12
 * @author: wxy
 * @create: 2020-01-30 16:18
 **/

@RestController
public class ProviderController {

    @Autowired
    SendService sendService;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendRequest(){
        @SuppressWarnings("rawtypes")
        Message message = MessageBuilder.withPayload("Hello".getBytes()).build();       //创建消息
        sendService.sendOrder().send(message);      //发送消息
        return "SUCCESS";
    }

}
