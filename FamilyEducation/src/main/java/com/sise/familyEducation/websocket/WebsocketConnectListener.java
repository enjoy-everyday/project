package com.sise.familyEducation.websocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: demo
 * @description:
 * @author: wxy
 * @create: 2020-02-12 15:55
 **/

@Slf4j
@Component
public class WebsocketConnectListener implements ApplicationListener<SessionConnectEvent> {

    static BidiMap<String, String> bidiMap = new DualHashBidiMap<>();

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        final StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println(stompHeaderAccessor);
        String sessionId = stompHeaderAccessor.getSessionId();
        HttpSession session;
        bidiMap.put(session.getAttribute("authentication"), sessionId);
        System.out.println(bidiMap);
    }

}
