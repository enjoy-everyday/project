package com.sise.hystrixClient.main;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommandProperties;
import com.sise.hystrixClient.command.AnotherCommand;

/**
 * @program: SpringCloud
 * @description: Lab8
 * @author: wxy
 * @create: 2020-01-22 15:59
 **/

public class SemaphoreIsoMain {

    public static void main(String[] args) throws InterruptedException {
        //配置使用信号量的策略进行隔离
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.execution.isolation.strategy", HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE);
        //设置最大并发数，默认值为10，这里是2
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests", 2);
        //设置执行回退方法的最大并发，默认为10，这里是20
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.fallback.isolation.semaphore.maxConcurrentRequests", 20);
        for (int i = 0; i < 6; i++){
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
