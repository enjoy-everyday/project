package com.sise.ribbonServer.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.sise.ribbonServer.entity.Person;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: SpringCloud
 * @description: Lab3
 * @author: wxy
 * @create: 2020-01-15 14:15
 **/

@RestController
public class RibbonController {

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public String findName(@PathVariable("personId") Integer personId, HttpServletRequest request){
        Person person = new Person(personId, "TOM", 23);
        person.setMessage(request.getRequestURL().toString());    //将请求保存在message中
        Map<String, String> parameterMap = Maps.newHashMap();
        parameterMap.put("PersonId", person.getPersonId().toString());
        parameterMap.put("Pname", person.getPname());
        parameterMap.put("Page", Integer.toString(person.getPage()));
        parameterMap.put("message", person.getMessage());
        String str = JSON.toJSONString(parameterMap);
        return str;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

}
