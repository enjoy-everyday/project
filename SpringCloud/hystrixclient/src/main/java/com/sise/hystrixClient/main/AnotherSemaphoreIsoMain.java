package com.sise.hystrixClient.main;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommandProperties;
import com.sise.hystrixClient.command.AnotherCommand;

/**
 * @program: SpringCloud
 * @description: Lab9 实验一
 * @author: wxy
 * @create: 2020-01-25 15:48
 **/

public class AnotherSemaphoreIsoMain {

    public static void main(String[] args) throws InterruptedException {
        //配置使用信号量的策略进行隔离
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.execution.isolation.strategy", HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE);
        //设置最大并发数，默认值为10，这里是2
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests", 6);
        //设置执行回退方法的最大并发，默认为10，这里是20
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.fallback.isolation.semaphore.maxConcurrentRequests", 20);
        for (int i = 0; i < 15; i++){
            int index = i;
            Thread thread = new Thread(){
                public void run(){
                    AnotherCommand anotherCommand = new AnotherCommand(index);
                    anotherCommand.execute();
                }
            };
            thread.start();
        }
        Thread.sleep(5000);
    }

}
