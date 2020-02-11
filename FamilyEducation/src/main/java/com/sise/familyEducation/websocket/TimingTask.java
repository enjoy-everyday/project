package com.sise.familyEducation.websocket;

import com.sise.familyEducation.service.HistoricalTaskService;
import com.sise.familyEducation.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
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
    private HistoricalTaskService historicalTaskService;

    private int oldTotal;

    @Override
    public void run(String... args) throws Exception {
        oldTotal = historicalTaskService.getTotal();
    }

    @Scheduled(cron = "0/5 * * * * ?")
    private void configureTasks() throws IOException {
        int newTotal = historicalTaskService.getTotal();
        if (newTotal > oldTotal){
            String message = "5555555";
            Iterator<Map.Entry<Session, String>> iterator = WebSocketServer.map.entrySet().iterator();
            Iterator<String> set = WebSocketServer.map.values().iterator();
            WebSocketServer webSocketServer = new WebSocketServer();
            while (iterator.hasNext()){
                Map.Entry<Session, String> entry = iterator.next();
                Session session = entry.getKey();
                String userPower = entry.getValue();
                System.out.println("推送内容：" + message);
                webSocketServer.sendMessage(session, message);
            }
            System.out.println("旧：" + oldTotal);
            System.out.println("新：" + newTotal);
            System.out.println("执行：" + LocalDateTime.now());
            oldTotal = newTotal;
        }
    }
}
