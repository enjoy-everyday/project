package com.sise.rabbit.main;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * @program: SpringCloud
 * @description: Lab12
 * @author: wxy
 * @create: 2020-01-30 15:44
 **/

public class Receive {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();      //创建连接
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();       //建立通道
        String queueName = "hello";     //声明队列
        channel.queueDeclare(queueName, false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel){       //创建消费者
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] bytes) throws UnsupportedEncodingException {
                String message = new String(bytes, "UTF-8");
                System.out.println("消息：" + message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

}
