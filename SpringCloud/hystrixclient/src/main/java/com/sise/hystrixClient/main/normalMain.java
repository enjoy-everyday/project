package com.sise.hystrixClient.main;

import com.sise.hystrixClient.command.Command;

/**
 * @program: SpringCloud
 * @description: Lab7 正常服务运行
 * @author: wxy
 * @create: 2020-01-21 17:11
 **/

public class normalMain {

    public static void main(String[] args){
        //正常服务
        String normalUrl = "http://localhost:8080/normal";
        Command command = new Command(normalUrl);
        String result = command.execute();
        System.out.println("正常服务：" + result);
    }

}
