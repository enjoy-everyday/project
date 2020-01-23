package com.sise.hystrixServer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: SpringCloud
 * @description: Lab7
 * @author: wxy
 * @create: 2020-01-21 16:37
 **/

@RestController
public class Controller {

    @GetMapping("/normal")
    @ResponseBody
    public String normal(HttpServletRequest httpServletRequest){
        return "Hystrix";
    }

    @GetMapping("/error")
    @ResponseBody
    public String error(HttpServletRequest httpServletRequest) throws InterruptedException {
        Thread.sleep(10000);
        return "Error";
    }

}
