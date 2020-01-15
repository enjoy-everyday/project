package com.sise.provider.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloud
 * @description: Lab2-2
 * @author: wxy
 * @create: 2020-01-14 11:44
 **/

@RestController
public class HealthController {

    public static boolean canVisitDb = false;
    @RequestMapping(value = "/db/{canVisitDb}", method = RequestMethod.GET)
    public String setConnectState(@PathVariable("canVisitDb") Boolean canVisitDb){
        HealthController.canVisitDb = canVisitDb;
        System.out.println("***********" + HealthController.canVisitDb);
        return "状态：" + HealthController.canVisitDb;
    }

}
