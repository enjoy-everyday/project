package com.sise.hystrixClient.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @program: SpringCloud
 * @description: Lab8
 * @author: wxy
 * @create: 2020-01-22 15:57
 **/

public class AnotherCommand extends HystrixCommand<String> {

    int index;

    public AnotherCommand(int index){
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup")));
        this.index = index;
    }

    @Override
    protected String run() throws Exception {
        Thread.sleep(500);
        System.out.println("执行方法");
        System.out.println("当前索引：" + index);
        return "";
    }

    protected String getFallback(){
        System.out.println("执行Fallback");
        System.out.println("当前索引：" + index);
        return "";
    }
}
