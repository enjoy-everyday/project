package com.sise.feignClient.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @program: SpringCloud
 * @description: Lab5 Feign
 * @author: wxy
 * @create: 2020-01-17 19:47
 **/

public interface People {

    @RequestLine("GET /{id}")
    Person findById(@Param("id") Integer id);

    @RequestLine("POST /create")
    @Headers("Content-Type: application/json")
    String createPerson(Person person);

    @RequestLine("POST /createXML")
    @Headers("Content-Type: application/xml")
    Result createPersonXML(Person person);

//    @Data             //为属性增加set get方法    JSON
    @XmlRootElement   //XML的编码与解码需要用到，不是需注释
    class Person{
        @XmlElement
        public Integer id;
        @XmlElement
        public String name;
        @XmlElement
        public Integer age;
        @XmlElement
        public String message;

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
        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

//    @Data        //JSON
    @XmlRootElement
    class Result{
        @XmlElement
        public String message;

        @XmlTransient
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
