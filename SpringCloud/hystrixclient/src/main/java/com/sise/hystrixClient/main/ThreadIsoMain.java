package com.sise.hystrixClient.main;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sise.hystrixClient.command.AnotherCommand;
import com.sise.hystrixClient.command.Command;

/**
 * @program: SpringCloud
 * @description: Lab8
 * @author: wxy
 * @create: 2020-01-22 15:51
 **/

public class ThreadIsoMain {

    public static void main(String[] args) throws InterruptedException {
        //配置线程池大小为3
        ConfigurationManager.getConfigInstance().setProperty("hystrix.threadpool.default.coreSize", 3);
        for (int i = 0; i < 6; i++){
            AnotherCommand anotherCommand = new AnotherCommand(i);
            anotherCommand.queue();
        }
        Thread.sleep(5000);
    }

}
