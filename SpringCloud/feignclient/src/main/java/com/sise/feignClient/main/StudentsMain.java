package com.sise.feignClient.main;

import com.sise.feignClient.client.People;
import com.sise.feignClient.client.Students;
import feign.Feign;
import feign.gson.GsonEncoder;

/**
 * @program: SpringCloud
 * @description: Lab5 实验
 * @author: wxy
 * @create: 2020-01-18 13:55
 **/

public class StudentsMain {

    public static void main(String[] args){
        Students students = Feign.builder().encoder(new GsonEncoder()).target(Students.class, "http://localhost:8080/student/");
        String student = students.findById(901);
        System.out.println(student);
    }

}
