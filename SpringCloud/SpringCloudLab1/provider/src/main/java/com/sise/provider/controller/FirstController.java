package com.sise.provider.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloudLab1
 * @description:
 * @author: wxy
 * @create: 2020-01-11 17:53
 **/

@RestController
public class FirstController {

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String findName(@PathVariable("name") String name){
        return name;
    }

}
