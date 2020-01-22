package com.sise.invoker.controller;

import com.netflix.appinfo.InstanceInfo;
import com.sise.invoker.client.Client;
import com.sise.invoker.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EurekaServiceInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private Client client;

//    @RequestMapping(value = "/invokeHello", method = RequestMethod.GET)
//    public String invokeHello(){
//        return client.hello();
//    }

    @RequestMapping(value = "/router", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String router(){
        Person person = client.getPerson(2);
        return person.getMessage();
    }

    /**
     * @date: 2020/1/20
     * @description: Lab6 实验2
     */

    @RequestMapping(value = "/custom", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String custom(){
        String springResult = client.customHello();
        System.out.println("@RequestMapping结果：" + springResult);
        String result = client.custom();
        System.out.println("自定义注解结果：" + result);
        return "控制台";
    }

}
