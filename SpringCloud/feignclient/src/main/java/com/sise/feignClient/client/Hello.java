package com.sise.feignClient.client;

import feign.RequestLine;

/**
 * @program: SpringCloud
 * @description: Lab5 Feign
 * @author: wxy
 * @create: 2020-01-17 18:21
 **/

public interface Hello {

    @RequestLine("GET /hello")
    String Hello();

}
