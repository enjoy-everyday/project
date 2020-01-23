package com.sise.hystrixClient.main;

import com.sise.hystrixClient.command.Command;

/**
 * @program: SpringCloud
 * @description: Lab7 异常服务运行
 * @author: wxy
 * @create: 2020-01-21 17:15
 **/

public class ErrorMain {

    public static void main(String[] args){
        //异常服务
        String errorUrl = "http://localhost:8080/error";
        Command command = new Command(errorUrl);
        String result = command.execute();
        System.out.println("异常服务：" + result);
    }

}
