package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.User;
import com.sise.familyEducation.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: FamilyEducation
 * @description: 登录
 * @author: wxy
 * @create: 2020-02-02 12:59
 **/

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * @date: 2020/2/3
     * @description: 测试
     */

    @ResponseBody
    @RequestMapping("/test")
    public String test(@RequestParam(value = "id") String id){
        return id;
    }

    @RequestMapping("/springboot")
    public String security(Authentication authentication){
        System.out.println("******" + authentication.getName() + "******");
        User user = loginService.findByPhone(authentication.getName());
//        User user = loginService.saveRole(1);
//        System.out.println(user);
//        System.out.println(user.getPhone());
//        System.out.println(user.getRole());
        return "forward:test?id=" + user.getId();
    }


//    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "home_page";
    }

    /**
     * @date: 2020/2/3
     * @description: 跳转页面
     */

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * @date: 2020/2/3
     * @description: 登录跳转
     */

    @RequestMapping("/home")
    public String login(Authentication authentication){
        User user = loginService.findByPhone(authentication.getName());
        String role = user.getRole().getRole();
        if (role.equals("学生")){
            System.out.println(user.getRole().getRole());
            return "student/student_home";
        }
        else if (role.equals("家长")){
            System.out.println(user.getRole().getRole());
            return "parent/parent_home";
        }
        else return "null";
    }

}
