package com.sise.hystrixClient.main;

import com.netflix.config.ConfigurationManager;
import com.sise.hystrixClient.command.AnotherCommand;

/**
 * @program: SpringCloud
 * @description: Lab9 实验一
 * @author: wxy
 * @create: 2020-01-25 15:46
 **/

public class AnotherThreadIsoMain {

    public static void main(String[] args) throws InterruptedException {
        //配置线程池大小为5
        ConfigurationManager.getConfigInstance().setProperty("hystrix.threadpool.default.coreSize", 5);
        for (int i = 0; i < 15; i++){
            AnotherCommand anotherCommand = new AnotherCommand(i);
            anotherCommand.queue();
        }
        Thread.sleep(5000);
    }
}
