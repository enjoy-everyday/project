package com.sise.hystrixClient.main;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.TimeUnit;

/**
 * @program: SpringCloud
 * @description: Lab7 实验2
 * @author: wxy
 * @create: 2020-01-22 13:54
 **/

public class AnotherFallbackMain extends HystrixCommand<String> {

    private Integer id;

    public AnotherFallbackMain(Integer id){
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.id = id;
    }

    protected String run() throws Exception{
        if (id % 2 == 0 && id <= 10){
            return "执行  " + id;
        }
        else {
            TimeUnit.MILLISECONDS.sleep(2000);
            return "";
        }
    }

    protected String getFallback(){
        System.out.println("回退  " + id);
        return "";
    }

    public static void main(String[] args){
        for (int i = 0; i < 30; i++){
            System.out.println(new AnotherFallbackMain(i).execute());
        }
    }

}
