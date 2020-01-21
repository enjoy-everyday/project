package com.sise.feignClient.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @program: SpringCloud
 * @description: Lab6 第三方注解
 * @author: wxy
 * @create: 2020-01-20 15:31
 **/

public interface RS {

    @GET
    @Path("/hello")
    public String Hello();

}
