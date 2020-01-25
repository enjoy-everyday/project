package com.sise.invoker.main;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @program: SpringCloud
 * @description: Lab9 feign整合
 * @author: wxy
 * @create: 2020-01-24 21:57
 **/

public class ClientMain {

    public static void main(String[] args) throws InterruptedException {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = loggerContext.getLogger("root");
        logger.setLevel(Level.toLevel("INFO"));
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        for (int i = 0; i < 6; i++){
            Thread thread = new Thread(){
                public void run(){
                    String url = "http://localhost:9000/feign/hello";
                    HttpGet httpGet = new HttpGet(url);
                    try {
                        HttpResponse httpResponse = httpClient.execute(httpGet);
                        System.out.println(EntityUtils.toString(httpResponse.getEntity()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
        Thread.sleep(15000);
    }

}
