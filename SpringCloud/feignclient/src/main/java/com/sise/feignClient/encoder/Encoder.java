package com.sise.feignClient.encoder;

import com.sise.feignClient.client.People;
import com.sise.feignClient.custom.CustomEncoder;
import feign.Feign;
import feign.gson.GsonEncoder;

/**
 * @program: SpringCloud
 * @description: 编码器
 * @author: wxy
 * @create: 2020-01-17 22:04
 **/

public class Encoder {

    /**
     * @date: 2020/1/18
     * @description: Lab5
     */

//    public static void main(String[] args){
//        People people = Feign.builder().encoder(new GsonEncoder()).target(People.class, "http://localhost:8080/");
//        People.Person person = new People.Person();
//        person.id = 1;
//        person.name = "TOM";
//        person.age = 30;
//        String response = people.createPerson(person);
//        System.out.println(response);
//    }

    /**
     * @date: 2020/1/20
     * @description: Lab6
     */

    public static void main(String[] args){
        People people = Feign.builder().encoder(new CustomEncoder()).target(People.class, "http://localhost:8080/");
        People.Person person = new People.Person();
        person.id = 1;
        person.name = "TOM";
        person.age = 30;
        String response = people.createPerson(person);
        System.out.println(response);
    }

}
