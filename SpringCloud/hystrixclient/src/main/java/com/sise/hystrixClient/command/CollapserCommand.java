package com.sise.hystrixClient.command;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sise.hystrixClient.entity.Person;

import java.util.*;

/**
 * @program: SpringCloud
 * @description: Lab8
 * @author: wxy
 * @create: 2020-01-22 16:25
 **/

public class CollapserCommand extends HystrixCommand<Map<String, Person>> {

    //请求集合，第一个类型是单个请求返回的数据类型，第二个是请求参数的类型
    Collection<HystrixCollapser.CollapsedRequest<Person, String>> collapsedRequests;

    public CollapserCommand(Collection<HystrixCollapser.CollapsedRequest<Person, String>> collapsedRequests){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup")));
        this.collapsedRequests = collapsedRequests;
    }

    @Override
    protected Map<String, Person> run() throws Exception {
        System.out.println("收集后执行命令");
        System.out.println("参数数量：" + collapsedRequests.size());
        //处理参数
        List<String> names = new ArrayList<String>();
        for (HystrixCollapser.CollapsedRequest<Person, String> request : collapsedRequests){
            names.add(request.getArgument());
        }
        Map<String, Person> result = callService(names);
        return result;
    }

    private Map<String, Person> callService(List<String> names){
        Map<String, Person> result = new HashMap<String, Person>();
        for (String name : names){
            Person person = new Person();
            person.setId(UUID.randomUUID().toString());
            person.setName(name);
            person.setAge(new Random().nextInt(30));
            result.put(name, person);
        }
        return result;
    }

}
