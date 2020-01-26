package com.sise.invoker.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sise.invoker.entity.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @program: SpringCloud
 * @description: Lab9 合并处理
 * @author: wxy
 * @create: 2020-01-24 15:50
 **/

@Component
public class CollapseService {

    //合并处理器由@HystrixCollapser注解实现
    //配置收集1秒内请求
    @HystrixCollapser(
            batchMethod = "getPeople", collapserProperties = {
                    @HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")
            }
    )
    public Future<Person> getSinglePerson(Integer id){
        System.out.println("单个获取方法");
        return null;
    }

    @HystrixCommand
    public List<Person> getPeople(List<Integer> ids){
        System.out.println("收集");
        System.out.println("数量：" + ids.size());
        List<Person> people = new ArrayList<Person>();
        for (Integer id : ids){
            Person person = new Person();
            person.setId(id);
            person.setName("sssssss");
            people.add(person);
        }
        return people;
    }

}
