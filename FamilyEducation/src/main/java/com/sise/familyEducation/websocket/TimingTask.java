package com.sise.familyEducation.websocket;

import com.sise.familyEducation.service.HistoricalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.websocket.Session;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: FamilyEducation
 * @description: 定时任务
 * @author: wxy
 * @create: 2020-02-10 11:42
 **/

@Configuration
@EnableScheduling
public class TimingTask implements CommandLineRunner {

    @Autowired
    private SimpMessagingTemplate template;        //广播推送消息

    @Scheduled(cron = "0/30 * * * * ?")
    private void configureTasks() throws IOException {
        System.out.println("后台广播推送！");
        this.template.convertAndSend("/topic/getResponse", "55555");
    }

    @Scheduled(cron = "0/10 * * * * ?")
    private void configureT() throws IOException {
        String value = "null";
        System.out.println("一对一推送！");

        if (WebsocketConnectListener.bidiMap.get("1") == null) {
            System.out.println("没有key");
        } else {
            System.out.println("发送");
            template.convertAndSendToUser("1", "/queue/getResponse", "6666");
        }
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
