package com.sise.hystrixClient.main;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @program: SpringCloud
 * @description: Lab7 熔断器打开时回退
 * @author: wxy
 * @create: 2020-01-22 11:36
 **/

public class FallbackMain {

    public static void main(String[] args){
        //强制打开熔断器
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.circuitBreaker.forceOpen", "true");
        FallbackCommand fallbackCommand1 = new FallbackCommand();
        fallbackCommand1.execute();
        //关闭熔断器，第二个命令
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.circuitBreaker.forceOpen", "false");
        FallbackCommand fallbackCommand2 = new FallbackCommand();
        fallbackCommand2.execute();
    }

    static class FallbackCommand extends HystrixCommand<String>{

        public FallbackCommand(){
            super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        }

        //如果熔断器强制打开，该方法不会执行
        protected String run() throws Exception{
            System.out.println("熔断器关闭，执行");
            return "";
        }

        //回退方法，熔断器打开后执行回退
        protected String getFallback(){
            System.out.println("熔断器打开，执行回退");
            return "Fallback";
        }

    }

}
