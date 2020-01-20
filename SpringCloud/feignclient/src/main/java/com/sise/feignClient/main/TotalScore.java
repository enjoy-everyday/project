package com.sise.feignClient.main;

import com.sise.feignClient.client.Students;
import feign.Feign;
import feign.gson.GsonDecoder;

import java.util.List;

/**
 * @program: SpringCloud
 * @description: Lab5 实验
 * @author: wxy
 * @create: 2020-01-18 13:25
 **/

public class TotalScore {

    public static void main(String[] args){
        Students students = Feign.builder().decoder(new GsonDecoder())      //GsonDecoder解码器，会将返回的响应JSON字符串转换为接口方法返回的对象
                .target(Students.class, "http://localhost:8080/student/");
        List<Students.Student> studentList = students.sum();
        System.out.println(students.sum());
        for (Students.Student student : studentList){
            System.out.println("学号：" + student.id + "，姓名：" + student.name + "，总成绩：" + (student.chinese + student.math + student.english));
        }
    }

}
