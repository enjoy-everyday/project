package com.sise.hystrixClient.entity;

/**
 * @program: SpringCloud
 * @description: Lab8
 * @author: wxy
 * @create: 2020-01-22 16:26
 **/

public class Person {

    private String id;
    private String name;
    private Integer age;

    public String toString(){
        return "id：" + id + "，name：" + name + "，age：" + age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
