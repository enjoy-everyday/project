package com.sise.feignClient.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * @program: SpringCloud
 * @description: Lab5 实验
 * @author: wxy
 * @create: 2020-01-18 11:33
 **/

public interface Students {


    @RequestLine("GET /{id}")
    @Headers("Content-Type: application/json")
    String findById(@Param("id") Integer id);

    @RequestLine("POST /sum")
    List<Student> sum();

    @RequestLine("POST /createStudent")
    @Headers("Content-Type: application/xml")
    String createStudentXML(Student student);

//    @Data
    @XmlRootElement
    class Student{
        @XmlElement
        public Integer id;
        @XmlElement
        public String name;
        @XmlElement
        public int chinese;
        @XmlElement
        public int math;
        @XmlElement
        public int english;
        @XmlElement
        public int average;

        @XmlTransient
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @XmlTransient
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @XmlTransient
        public int getChinese() {
            return chinese;
        }

        public void setChinese(int chinese) {
            this.chinese = chinese;
        }

        @XmlTransient
        public int getMath() {
            return math;
        }

        public void setMath(int math) {
            this.math = math;
        }

        @XmlTransient
        public int getEnglish() {
            return english;
        }

        public void setEnglish(int english) {
            this.english = english;
        }

        @XmlTransient
        public int getAverage() {
            return average;
        }

        public void setAverage(int average) {
            this.average = average;
        }
    }

}
