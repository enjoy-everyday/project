package com.sise.dbService.controller;

import com.sise.dbService.entity.User;
import com.sise.dbService.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: SpringCloud
 * @description: 综合实验
 * @author: wxy
 * @create: 2020-01-31 12:02
 **/

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/save")
    public Map<String, Object> save(@RequestBody User user){
        userService.save(user);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "success");
        return map;
    }

    @GetMapping(value = "/user/{uid}")
    @ResponseBody
    public User findUser(@PathVariable(value = "uid") String uid){
        User user = userService.findById(uid);
        return user;
    }

}
