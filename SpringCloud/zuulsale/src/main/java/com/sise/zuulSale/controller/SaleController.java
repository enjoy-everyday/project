package com.sise.zuulSale.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloud
 * @description: Lab11
 * @author: wxy
 * @create: 2020-01-28 15:35
 **/

@RestController
public class SaleController {

    @RequestMapping(value = "/errorTest", method = RequestMethod.GET)
    public String errorTest() throws InterruptedException {
        Thread.sleep(3000);
        return "error Fallback";
    }

}
