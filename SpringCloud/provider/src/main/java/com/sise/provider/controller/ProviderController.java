package com.sise.provider.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.sise.provider.entity.Person;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: SpringCloud
 * @description:
 * @author: wxy
 * @create: 2020-01-13 12:00
 **/

@RestController
public class ProviderController {

    /**
     * @date: 2020/1/12
     * @description: Lab1
     */

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public String findName(@PathVariable("personId") Integer personId){
        Person person = new Person(personId, "TOM", 23);
        Map<String, String> parameterMap = Maps.newHashMap();
        parameterMap.put("PersonId", person.getPersonId().toString());
        parameterMap.put("Pname", person.getPname());
        parameterMap.put("Page", Integer.toString(person.getPage()));
        String str = JSON.toJSONString(parameterMap);
        return str;
    }
}
