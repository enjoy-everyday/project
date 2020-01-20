package com.sise.restServer.entity;

/**
 * @program: SpringCloud
 * @description: Lab5 实验
 * @author: wxy
 * @create: 2020-01-18 11:40
 **/

public class Student {

    private Integer id;
    private String  name;
    private int chinese;
    private int math;
    private int english;

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

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }
}
