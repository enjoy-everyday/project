package com.sise.hystrixClient.main;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;


/**
 * @program: SpringCloud
 * @description: Lab8
 * @author: wxy
 * @create: 2020-01-22 19:34
 **/

public class CacheMain {

    public static void main(String[] args){
        //初始化请求上下文
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        //请求正常服务
        String key = "cache-key";
        Command command = new Command(key);
        Command command1 = new Command(key);
        Command command2 = new Command(key);
        //输出结果
        System.out.println(command.execute() + "第一个是否读取缓存：" + command.isResponseFromCache());
        System.out.println(command1.execute() + "第二个是否读取缓存：" + command1.isResponseFromCache());
        System.out.println(command2.execute() + "第三个是否读取缓存：" + command2.isResponseFromCache());
        //获取缓存实例
        HystrixRequestCache hystrixRequestCache = HystrixRequestCache.getInstance(HystrixCommandKey.Factory.asKey("MyCommandKey"),
                HystrixConcurrencyStrategyDefault.getInstance());
        //清空缓存
        hystrixRequestCache.clear(key);
        //重新执行命令
        Command command3 = new Command(key);
        System.out.println(command3.execute() + "第四个是否读取缓存：" + command3.isResponseFromCache());
        hystrixRequestContext.shutdown();
    }

    static class Command extends HystrixCommand<String>{

        private String key;

        public Command(String key){
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("MyCommandKey")));
            this.key = key;
        }

        @Override
        protected String run() throws Exception {
            System.out.println("执行");
            return "";
        }

        protected String getCacheKey(){
            return this.key;
        }

    }

}
