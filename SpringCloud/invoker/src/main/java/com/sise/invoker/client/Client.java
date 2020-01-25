package com.sise.invoker.client;

import com.sise.invoker.custom.MyUrl;
import com.sise.invoker.entity.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @program: SpringCloud
 * @description: Lab6
 * @author: wxy
 * @create: 2020-01-20 17:10
 **/

//@FeignClient(name = "provider")
//public interface Client {
//
////    @RequestMapping(method = RequestMethod.GET, value = "/hello")
////    String hello();
//
//    @RequestMapping(method = RequestMethod.GET, value = "/{personId}")
//    Person getPerson(@PathVariable("personId") Integer personId);
//
//    @MyUrl(method = "GET", url = "/hello")
//    String custom();
//
//    @RequestMapping(method = RequestMethod.GET, value = "/hello")
//    String customHello();
//
//}

    /**
     * @date: 2020/1/24
     * @description: Lab9
     */

    @FeignClient(name = "provider", fallback = Client.ClientFallback.class)
    public interface Client {

        @RequestMapping(method = RequestMethod.GET, value = "/hello")
        public String hello();

        @Component
        static class ClientFallback implements Client{

            public String hello(){
                return "error";
            }

        }

    }

