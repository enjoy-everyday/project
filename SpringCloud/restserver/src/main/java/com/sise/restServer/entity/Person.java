package com.sise.restServer.entity;

/**
 * @program: SpringCloud
 * @description: Lab5 CXF
 * @author: wxy
 * @create: 2020-01-17 14:51
 **/

public class Person {

    private Integer id;
    private String name;
    private int age;
    private String message;   //保存请求的URL

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
