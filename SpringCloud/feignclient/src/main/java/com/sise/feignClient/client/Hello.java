package com.sise.feignClient.client;

import com.sise.feignClient.custom.CustomAnnotation;
import feign.RequestLine;

/**
 * @program: SpringCloud
 * @description: Feign
 * @author: wxy
 * @create: 2020-01-17 18:21
 **/

public interface Hello {

    /**
     * @date: 2020/1/18
     * @description: Lab5
     */

//    @RequestLine("GET /hello")
//    String Hello();

    /**
     * @date: 2020/1/20
     * @description: Lab6
     */

    @CustomAnnotation(method = "GET", url = "/hello")
    String Hello();

}
