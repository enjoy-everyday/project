package com.sise.provider.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.sise.provider.entity.Person;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: SpringCloud
 * @description: provider
 * @author: wxy
 * @create: 2020-01-11 17:53
 **/

@RestController
public class ProviderController {

    /**
     * @date: 2020/1/12
     * @description: Lab1
     */

//    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
//    public String findName(@PathVariable("personId") Integer personId){
//        Person person = new Person(personId, "TOM", 23);
//        Map<String, String> parameterMap = Maps.newHashMap();
//        parameterMap.put("PersonId", person.getPersonId().toString());
//        parameterMap.put("Pname", person.getPname());
//        parameterMap.put("Page", Integer.toString(person.getPage()));
//        String str = JSON.toJSONString(parameterMap);
//        return str;
//    }


    /**
     * @date: 2020/1/13
     * @description: Lab2 and Lab4
     */

//    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
//    public String findName(@PathVariable("personId") Integer personId, HttpServletRequest request){
//        Person person = new Person(personId, "TOM", 23);
//        person.setMessage(request.getRequestURL().toString());    //将请求保存在message中
//        Map<String, String> parameterMap = Maps.newHashMap();
//        parameterMap.put("PersonId", person.getPersonId().toString());
//        parameterMap.put("Pname", person.getPname());
//        parameterMap.put("Page", Integer.toString(person.getPage()));
//        parameterMap.put("message", person.getMessage());
//        String str = JSON.toJSONString(parameterMap);
//        return str;
//    }

    /**
     * @date: 2020/1/20
     * @description: Lab6
     */

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public Person findPerson(@PathVariable("personId") Integer personId, HttpServletRequest request){
        Person person = new Person(personId, "TOM", 23);
        person.setMessage(request.getRequestURL().toString());
        return person;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        return "Hello";
    }

}
