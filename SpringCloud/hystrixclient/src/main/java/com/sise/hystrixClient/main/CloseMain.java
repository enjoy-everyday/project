package com.sise.hystrixClient.main;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @program: SpringCloud
 * @description: Lab8
 * @author: wxy
 * @create: 2020-01-22 15:05
 **/

public class CloseMain {

    public static void main(String[] args) throws InterruptedException {
        //10秒内有3个请求满足开启断路器的第一个条件
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.metrics.rollingStats.timeInMilliseconds", 10000);
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.circuitBreaker.requestVolumeThreshold", 3);
        //请求失败率，默认值为50%
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.circuitBreaker.errorThresholdPercentage", 50);
        //设置休眠期，断路器开启后，进入到不再执行命令的休眠期，默认为5秒，这里3秒
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds", 3000);
        //该值决定是否执行超时
        boolean isTimeout = true;
        for (int i = 0; i < 10; i++){
            Command command = new Command(isTimeout);
            command.execute();
            //输出健康状态等信息
            HystrixCommandMetrics.HealthCounts healthCounts = command.getMetrics().getHealthCounts();
            System.out.println("断路器状态：" + command.isCircuitBreakerOpen());
            System.out.println("请求总数：" + healthCounts.getTotalRequests());
            if (command.isCircuitBreakerOpen()){
                isTimeout = false;
                System.out.println("******断路器打开，等待休眠结束******");
                //休眠期会在3秒后结束，等待4秒，确保结束
                Thread.sleep(4000);
            }
        }
    }

    /**
     * @date: 2020/1/22
     * @description: 模拟超时命令
     */

    static class Command extends HystrixCommand<String> {

        private boolean isTimeout;

        public Command(boolean isTimeout) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
            this.isTimeout = isTimeout;
        }

        @Override
        protected String run() throws Exception {
            if (isTimeout) {
                Thread.sleep(800);
            } else {
                Thread.sleep(200);
            }
            return "";
        }

        protected String getFallback() {
            return "";
        }
    }
}
