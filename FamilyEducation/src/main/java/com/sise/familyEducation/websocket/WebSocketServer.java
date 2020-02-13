//package com.sise.familyEducation.websocket;
//
//import org.springframework.stereotype.Component;
//
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.EnumMap;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @program: FamilyEducation
// * @description: websocket服务端
// * @author: wxy
// * @create: 2020-02-10 16:26
// **/
//
//@Component
//@ServerEndpoint(value = "/ws/{userName}")
//public class WebSocketServer {
//
//    static Map<Session, String> map = new HashMap<>();
//
//    @OnOpen
//    public void OnOpen(@PathParam("userName") String userName, Session session){
//        System.out.println(userName);
//        map.put(session, userName);
//        System.out.println("OnOpen()方法执行");
//        System.out.println("连接建立成功");
//    }
//
//    @OnClose
//    public void OnClose(Session session){
//        if (map.containsKey(session)){
//            map.remove(session);
//        }
//        System.out.println("OnClose()方法执行");
//        System.out.println("连接关闭");
//    }
//
//    @OnMessage
//    public void OnMessage(String msg, Session session){
//        System.out.println("接收客户端消息：" + msg);
//        System.out.println("发送数据");
//    }
//
//    @OnError
//    public void OnError(Session session, Throwable error){
//        if (map.containsKey(session)){
//            map.remove(session);
//        }
//        System.out.println("OnError方法被执行");
//        System.out.println("出错");
//    }
//
//    public void sendMessage(Session session, String message) throws IOException {
//        session.getBasicRemote().sendText(message);
//    }
//
//}
