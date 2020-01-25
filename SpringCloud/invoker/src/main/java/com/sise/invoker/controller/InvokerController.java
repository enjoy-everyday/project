package com.sise.invoker.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.sise.invoker.client.Client;
import com.sise.invoker.entity.Person;
import com.sise.invoker.exception.MyException;
import com.sise.invoker.service.CacheService;
import com.sise.invoker.service.CollapseService;
import com.sise.invoker.service.InvokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EurekaServiceInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @program: SpringCloud
 * @description: Invoker
 * @author: wxy
 * @create: 2020-01-11 18:12
 **/

@RestController
@Configuration
public class InvokerController {

    /**
     * @date: 2020/1/12
     * @description: Lab1
     */

//    @Bean
//    @LoadBalanced
//    public RestTemplate getRestTemplate(){
//        return new RestTemplate();
//    }
//
//    @RequestMapping(value = "router", method = RequestMethod.GET)
//    public String router(){
//        RestTemplate restTemplate = getRestTemplate();
//        String str = restTemplate.getForObject("http://provider/9001", String.class);
//        return str;
//    }

    /**
     * @date: 2020/1/14
     * @description: Lab2-2
     */

//    @Autowired
//    private DiscoveryClient discoveryClient;
//    @RequestMapping(value = "/router", method = RequestMethod.GET)
//    public String router(){
//        List<ServiceInstance> instances = getServiceInstance();
//        for (ServiceInstance serviceInstance : instances){
//            EurekaServiceInstance eurekaServiceInstance = (EurekaServiceInstance) serviceInstance;
//            InstanceInfo instanceInfo = eurekaServiceInstance.getInstanceInfo();
//            System.out.println(instanceInfo.getAppName() + "***" + instanceInfo.getInstanceId() + "***" + instanceInfo.getStatus());
//        }
//        return "";
//    }
//
//    private List<ServiceInstance> getServiceInstance() {
//        List<String> ids = discoveryClient.getServices();
//        List<ServiceInstance> result = new ArrayList<ServiceInstance>();
//        for (String id : ids){
//            List<ServiceInstance> instances = discoveryClient.getInstances(id);
//            result.addAll(instances);
//        }
//        return result;
//    }

    /**
     * @date: 2020/1/16
     * @description: Lab4
     */

//    @Bean
//    @LoadBalanced
//    public RestTemplate getRestTemplate(){
//        return new RestTemplate();
//    }
//
//    @RequestMapping(value = "router", method = RequestMethod.GET)
//    public String router(){
//        RestTemplate restTemplate = getRestTemplate();
//        String str = restTemplate.getForObject("http://provider/9001", String.class);
//        return str;
//    }

    /**
     * @date: 2020/1/20
     * @description: Lab6
     */

//    @Autowired
//    private Client client;
//
////    @RequestMapping(value = "/invokeHello", method = RequestMethod.GET)
////    public String invokeHello(){
////        return client.hello();
////    }
//
//    @RequestMapping(value = "/router", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public String router(){
//        Person person = client.getPerson(2);
//        return person.getMessage();
//    }

    /**
     * @date: 2020/1/20
     * @description: Lab6 实验2
     */

//    @RequestMapping(value = "/custom", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public String custom(){
//        String springResult = client.customHello();
//        System.out.println("@RequestMapping结果：" + springResult);
//        String result = client.custom();
//        System.out.println("自定义注解结果：" + result);
//        return "控制台";
//    }

    /**
     * @date: 2020/1/24
     * @description: Lab9
     */

    @Autowired
    private InvokerService invokerService;

    @RequestMapping(value = "/router/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person router(@PathVariable Integer id){
        Person person = invokerService.getPerson(id);
        return person;
    }

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public String config() throws InterruptedException {
        String result = invokerService.Config();
        return result;
    }

    @RequestMapping(value = "exception", method = RequestMethod.GET)
    public String exception() throws MyException {
        String result = invokerService.TException();
        return result;
    }

    /**
     * @date: 2020/1/24
     * @description: Lab9 缓存注解
     */

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "/cache/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person cacheResult(@PathVariable Integer id){
        //多次调用服务
        for (int i = 0; i < 3; i++){
            Person person = cacheService.getPerson(id);
            System.out.println("调用服务：" + i);
        }
        return new Person();
    }

    @RequestMapping(value = "/cache1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String cacheRemove(){
        for (int i = 0; i < 3; i++){
            cacheService.cacheMethod("aaaaaa");
            System.out.println("******缓存失败******");
        }
        for (int i = 0; i < 3; i++){
            cacheService.cacheMethod("aaaaaa");
            System.out.println("调用服务：" + i);
        }
        return "";
    }

    /**
     * @date: 2020/1/24
     * @description: 合并请求
     */

    @Autowired
    private CollapseService collapseService;

    @RequestMapping(value = "/collapse", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String collapse() throws ExecutionException, InterruptedException {
        Future<Person> future = collapseService.getSinglePerson(1);
        Future<Person> future1 = collapseService.getSinglePerson(2);
        Future<Person> future2 = collapseService.getSinglePerson(3);
        Person person = future.get();
        Person person1 = future1.get();
        Person person2 = future2.get();
        System.out.println(person.getId() + "******" + person.getName());
        System.out.println(person1.getId() + "******" + person1.getName());
        System.out.println(person2.getId() + "******" + person2.getName());
        return "";
    }

    /**
     * @date: 2020/1/24
     * @description: Feign整合
     */

    @Autowired
    Client client;

    @RequestMapping(value = "/feign/hello", method = RequestMethod.GET)
    public String feignHello(){
        String result = client.hello();
        HystrixCircuitBreaker breaker = HystrixCircuitBreaker.Factory.getInstance(HystrixCommandKey.Factory.asKey("Client#hello()"));
        System.out.println("断路器状态：" + breaker.isOpen());
        return result;
    }

}
