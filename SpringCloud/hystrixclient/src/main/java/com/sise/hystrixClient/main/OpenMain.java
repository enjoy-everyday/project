package com.sise.hystrixClient.main;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @program: SpringCloud
 * @description: Lab8
 * @author: wxy
 * @create: 2020-01-22 14:37
 **/

public class OpenMain {

    public static void main(String[] args){
        //10秒内有10个请求，满足第一个断路器开启条件
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.metrics.rollingStats.timeInMilliseconds", 10000);
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.circuitBreaker.requestVolumeThreshold", 10);
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.circuitBreaker.errorThresholdPercentage", 50);
        for (int i = 0; i < 15; i++){
            //执行15次命令请求
            Command command = new Command();
            command.execute();
            //断路器是否打开
            if (command.isCircuitBreakerOpen()){
                System.out.println("断路器打开，执行第" + (i + 1) + "个命令");
            }
        }
    }

    /**
     * @date: 2020/1/22
     * @description: 模拟超时命令
     */

    static class Command extends HystrixCommand<String>{

        public Command(){
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
        }

        @Override
        protected String run() throws Exception {
            Thread.sleep(800);
            return "";
        }

        protected String getFallback(){
            return "";
        }

    }

}
