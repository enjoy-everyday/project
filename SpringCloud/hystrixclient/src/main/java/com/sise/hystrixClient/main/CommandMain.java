package com.sise.hystrixClient.main;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @program: SpringCloud
 * @description: Lab7 实验1
 * @author: wxy
 * @create: 2020-01-22 13:38
 **/

public class CommandMain extends HystrixCommand<String> {

    private final String name;

    public CommandMain(String name) {
        //最少配置：指定命令组名
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    protected String run() throws Exception{
        //依赖配置逻辑封装在run()方法中
        return "Hello" + name + "！Thread：" + Thread.currentThread().getName();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        CommandMain commandMain = new CommandMain("Synchronous-hystrix");
        //使用execute()同步调用代码，效果相当于hCommand.queue().get()
        String string = commandMain.execute();
        System.out.println("******同步******");
        System.out.println(string);
        //异步调用，可以自由控制获取结果的时间
        CommandMain commandMain1 = new CommandMain("Asynchronous-hystrix");
        Future<String> future = commandMain1.queue();
        //get()操作不能超过command定义的超时时间，默认为1秒
        string = future.get(100, TimeUnit.MILLISECONDS);
        System.out.println("******异步******");
        System.out.println(string);
        System.out.println("******主函数******");
        System.out.println(Thread.currentThread().getName());
    }

}
