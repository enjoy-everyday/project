package com.sise.hystrixClient.collapser;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.sise.hystrixClient.command.CollapserCommand;
import com.sise.hystrixClient.entity.Person;

import java.util.Collection;
import java.util.Map;

/**
 * @program: SpringCloud
 * @description: Lab8
 * @author: wxy
 * @create: 2020-01-22 16:44
 **/

public class Collapser extends HystrixCollapser<Map<String, Person>, Person, String> {

    String name;

    public Collapser(String name){
        this.name = name;
    }

    @Override
    public String getRequestArgument() {
        return name;
    }

    @Override
    protected HystrixCommand<Map<String, Person>> createCommand(Collection<CollapsedRequest<Person, String>> collapsedRequests) {
        return new CollapserCommand(collapsedRequests);
    }

    @Override
    protected void mapResponseToRequests(Map<String, Person> batchResponse, Collection<CollapsedRequest<Person, String>> collapsedRequests) {
        //让请求与结果关联
        for (CollapsedRequest<Person, String> request : collapsedRequests){
            //获取单个相应返回结果
            Person result = batchResponse.get(request.getArgument());
            //关联请求中
            request.setResponse(result);
        }
    }
}
