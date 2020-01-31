package com.sise.rabbit.main;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: SpringCloud
 * @description: Lab12
 * @author: wxy
 * @create: 2020-01-30 15:37
 **/

public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();      //创建连接
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();       //建立通道
        String queueName = "hello";     //声明队列
        channel.queueDeclare(queueName, false, false, false, null);
        String message = "Hello";
        channel.basicPublish("", queueName, null, message.getBytes());      //进行消息发布
        channel.close();
        connection.close();
    }

}
