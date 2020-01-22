package com.sise.invoker.entity;

/**
 * @program: SpringCloud
 * @description: Lab6
 * @author: wxy
 * @create: 2020-01-20 22:31
 **/

public class Person {

    private Integer id;
    private String name;
    private int age;
    private String message;   //保存请求的URL

    public Person(){
        super();
    }

    public Person(Integer id, String name, int age){
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }

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
