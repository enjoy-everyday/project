package com.sise.hystrixClient.main;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.sise.hystrixClient.collapser.Collapser;
import com.sise.hystrixClient.entity.Person;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @program: SpringCloud
 * @description: Lab8
 * @author: wxy
 * @create: 2020-01-22 16:51
 **/

public class CollapseMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //收集1秒内发生的请求，合并为一个命令执行
        ConfigurationManager.getConfigInstance().setProperty("hystrix.collapser.default.timerDelayInMilliseconds", 1000);
        //请求上下文
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        //创建请求合并处理器
        Collapser collapser = new Collapser("aaa");
        Collapser collapser1 = new Collapser("bbb");
        Collapser collapser2 = new Collapser("ccc");
        Collapser collapser3 = new Collapser("ddd");
        //异步执行
        Future<Person> future = collapser.queue();
        Future<Person> future1 = collapser1.queue();
        Future<Person> future2 = collapser2.queue();
        Future<Person> future3 = collapser3.queue();
        System.out.println(future.get());
        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
        hystrixRequestContext.shutdown();
    }

}
