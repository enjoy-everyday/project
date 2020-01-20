package com.sise.feignClient.main;

import com.sise.feignClient.client.Students;
import feign.Feign;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBDecoder;
import feign.jaxb.JAXBEncoder;

/**
 * @program: SpringCloud
 * @description: Lab5 实验
 * @author: wxy
 * @create: 2020-01-18 15:59
 **/

public class AverageScore {

    public static void main(String[] args){
        JAXBContextFactory jaxbContextFactory = new JAXBContextFactory.Builder().build();
        Students students = Feign.builder().encoder(new JAXBEncoder(jaxbContextFactory)).target(Students.class, "http://localhost:8080/student/");
        Students.Student student = new Students.Student();
        student.id = 901;
        student.name = "TOMMY";
        student.chinese = 88;
        student.math = 80;
        student.english = 75;
        String  student1 = students.createStudentXML(student);
        System.out.println(student1);
    }

}
