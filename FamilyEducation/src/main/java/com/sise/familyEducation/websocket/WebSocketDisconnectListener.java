package com.sise.familyEducation.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * @program: demo
 * @description:
 * @author: wxy
 * @create: 2020-02-12 16:00
 **/

@Slf4j
@Component
public class WebSocketDisconnectListener  implements ApplicationListener<SessionDisconnectEvent> {

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
//        //获取SessionId
        String sessionId = sha.getSessionId();
//        log.info("sessionId: {} 断开连接",sessionId);
        WebsocketConnectListener.bidiMap.removeValue(sessionId);
        System.out.println(WebsocketConnectListener.bidiMap);
        System.out.println(sessionId);
        System.out.println(sha);
    }

}
