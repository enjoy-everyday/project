package com.sise.zuulSale.service;

import com.sise.zuulSale.entity.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @program: SpringCloud
 * @description: Lab10 实验
 * @author: wxy
 * @create: 2020-01-27 15:03
 **/

@FeignClient("person-server")
public interface PersonService {

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    Person getPerson(@PathVariable("id") Integer id);

}
