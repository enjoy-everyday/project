package com.sise.rabbitProvider.controller;

import com.sise.rabbitProvider.client.UserClient;
import com.sise.rabbitProvider.entity.User;
import com.sise.rabbitProvider.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
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

//    @Autowired
//    SendService sendService;
//
//    @RequestMapping(value = "/send", method = RequestMethod.GET)
//    public String sendRequest(){
//        @SuppressWarnings("rawtypes")
//        Message message = MessageBuilder.withPayload("Hello".getBytes()).build();       //创建消息
//        sendService.sendOrder().send(message);      //发送消息
//        return "SUCCESS";
//    }

    /**
     * @date: 2020/1/31
     * @description: 综合实验
     */

    @Autowired
    SendService sendService;

    @Autowired
    private UserClient userClient;

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/send/{uid}", method = RequestMethod.GET)
    public String sendRequest(@PathVariable(value = "uid") String uid){
        User user = userClient.getUser(uid);
        String info = user.getUid() + "    " + user.getUsername();
        Message message = MessageBuilder.withPayload(info.getBytes()).build();       //创建消息
        sendService.sendOrder().send(message);      //发送消息
        return "SUCCESS";
    }


}
