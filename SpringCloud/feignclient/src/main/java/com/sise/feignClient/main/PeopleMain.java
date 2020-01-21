package com.sise.feignClient.main;

import com.sise.feignClient.client.People;
import com.sise.feignClient.custom.CustomDecoder;
import feign.Feign;
import feign.gson.GsonDecoder;
import com.sise.feignClient.client.People.Person;

/**
 * @program: SpringCloud
 * @description: Feign
 * @author: wxy
 * @create: 2020-01-17 19:52
 **/

public class PeopleMain {

    /**
     * @date: 2020/1/18
     * @description: Lab5
     */

//    public static void main(String[] args){
//        People people = Feign.builder().decoder(new GsonDecoder())      //GsonDecoder解码器，会将返回的响应JSON字符串转换为接口方法返回的对象
//                                       .target(People.class, "http://localhost:8080/");
//        Person person = people.findById(2);
//        System.out.println(person.id + "-" + person.name + "-" + person.age + "-" +person.message);
//    }

    /**
     * @date: 2020/1/20
     * @description: Lab6
     */

    public static void main(String[] args){
        People people = Feign.builder().decoder(new CustomDecoder())
                .target(People.class, "http://localhost:8080/");
        Person person = people.findById(2);
        System.out.println(person.id + "-" + person.name + "-" + person.age + "-" +person.message);
    }

}
