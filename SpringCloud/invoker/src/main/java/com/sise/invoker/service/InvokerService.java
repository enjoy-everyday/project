package com.sise.invoker.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sise.invoker.entity.Person;
import com.sise.invoker.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SpringCloud
 * @description: Lab9
 * @author: wxy
 * @create: 2020-01-24 11:38
 **/

@Component
public class InvokerService {

    @Autowired
    private RestTemplate restTemplate;      //注入RestTemplate调用Eureka

    @HystrixCommand(fallbackMethod = "getPersonFallback")       //@HystrixCommand注解修饰方法getPerson,并且配置了回退方法
    public Person getPerson(Integer id){
        //调用Eureka
        Person person = restTemplate.getForObject("http://provider/{id}", Person.class, id);
        return person;
    }

    //回退方法
    public Person getPersonFallback(Integer id){
        Person person = new Person();
        person.setId(0);
        person.setName("回退");
        person.setAge(-1);
        person.setMessage("error");
        return person;
    }

    //测试配置，设置命令执行超过时间为1000ms，设置命令执行的线程大小为1
    @HystrixCommand(        //声明一个命令
            fallbackMethod = "ConfigFallback", groupKey = "MyGroup",
            commandKey = "MyCommandKey", threadPoolKey = "MyCommandPool",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1")
            }
    )
    public String Config() throws InterruptedException {
        Thread.sleep(1500);
        return "ok";
    }

    public String ConfigFallback(){
        return "超时错误";
    }

    //如果方法抛出MyException，不会触发回退
    @HystrixCommand(fallbackMethod = "ExceptionFallback")
    public String TException() throws MyException {
        throw new MyException();
    }

    public String ExceptionFallback(){
        return "异常错误";
    }

}
