package com.sise.provider.entity;

/**
 * @program: SpringCloudLab1
 * @description:
 * @author: wxy
 * @create: 2020-01-12 12:38
 **/

public class Person {


    /**
     * @date: 2020/1/12
     * @description: Lab1
     */
//    Integer PersonId;
//    String Pname;
//    int Page;
//
//    public Person(Integer personId, String pname, int page){
//        PersonId = personId;
//        Pname = pname;
//        Page = page;
//    }
//
//    public Integer getPersonId() {
//        return PersonId;
//    }
//
//    public void setPersonId(Integer personId) {
//        PersonId = personId;
//    }
//
//    public String getPname() {
//        return Pname;
//    }
//
//    public void setPname(String pname) {
//        Pname = pname;
//    }
//
//    public int getPage() {
//        return Page;
//    }
//
//    public void setPage(int page) {
//        Page = page;
//    }


    /**
     * @date: 2020/1/13
     * @description: Lab2
     */

    Integer PersonId;
    String Pname;
    int Page;
    String message;   //保存请求的URL

    public Person(Integer personId, String pname, int page){
        PersonId = personId;
        Pname = pname;
        Page = page;
    }

    public Integer getPersonId() {
        return PersonId;
    }

    public void setPersonId(Integer personId) {
        PersonId = personId;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public int getPage() {
        return Page;
    }

    public void setPage(int page) {
        Page = page;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
