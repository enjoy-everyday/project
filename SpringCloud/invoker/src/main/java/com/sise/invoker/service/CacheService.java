package com.sise.invoker.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.sise.invoker.entity.Person;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud
 * @description: Lab9
 * @author: wxy
 * @create: 2020-01-24 15:30
 **/

@Component
public class CacheService {

    @CacheResult        //被修饰方法返回结果将会被缓存，需要与@HystrixCommand一起使用
    @HystrixCommand
    public Person getPerson(Integer id){
        System.out.println("getPerson()");
        Person person = new Person();
        person.setId(id);
        person.setName("Tommy");
        return person;
    }

    @CacheResult
    @HystrixCommand(commandKey = "removeKey")
    public String cacheMethod(String name){
        System.out.println("cacheMethod()");
        return "Hello";
    }

    @CacheRemove(commandKey = "removeKey")      //被修饰方法不使用缓存，缓存失效，需要与@CacheResult的缓存key关联
    @HystrixCommand
    public String updateMethod(String name){
        return "update";
    }

}
