package com.sise.feignClient.encoder;

import com.sise.feignClient.client.People;
import feign.Feign;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBDecoder;
import feign.jaxb.JAXBEncoder;

/**
 * @program: SpringCloud
 * @description: Lab5 XML的解码与编码
 * @author: wxy
 * @create: 2020-01-17 22:23
 **/

public class XML {

    public static void main(String[] args){
        JAXBContextFactory jaxbContextFactory = new JAXBContextFactory.Builder().build();
        People people = Feign.builder().encoder(new JAXBEncoder(jaxbContextFactory)).decoder(new JAXBDecoder(jaxbContextFactory)).target(People.class, "http://localhost:8080/");
        People.Person person = new People.Person();
        person.id = 1;
        person.name = "JACK";
        person.age = 28;
        People.Result result = people.createPersonXML(person);
        System.out.println(result.message);
    }

}
