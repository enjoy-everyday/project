package com.sise.rabbitProvider.client;

import com.sise.rabbitProvider.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @program: SpringCloud
 * @description: 综合实验
 * @author: wxy
 * @create: 2020-01-31 13:11
 **/

@FeignClient("dbService")
public interface UserClient {

    @RequestMapping(value = "/user/{uid}", method = RequestMethod.GET)
    User getUser(@PathVariable("uid") String uid);

}
