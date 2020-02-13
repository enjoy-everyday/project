package com.sise.familyEducation.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @program: FamilyEducation
 * @description: websocket配置
 * @author: wxy
 * @create: 2020-02-10 15:45
 **/

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/endpointOyzc").withSockJS();//注册STOMP协议的节点，映射指定的URL，并指定使用SockJS协议
        stompEndpointRegistry.addEndpoint("/endpointOne").withSockJS();//注册STOMP协议的节点，映射指定的URL，并指定使用SockJS协议
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代码（Message Broker）
        registry.enableSimpleBroker("/topic", "/user");//广播式应配置一个/topic消息代理

        //点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/

        registry.setUserDestinationPrefix("/user");
    }
}
