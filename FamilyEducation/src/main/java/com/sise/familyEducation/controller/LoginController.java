package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.Role;
import com.sise.familyEducation.entity.User;
import com.sise.familyEducation.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/springboot")
    public String security(){
        User user = loginService.saveRole(1);
        System.out.println(user);
        System.out.println(user.getPhone());
        System.out.println(user.getRole());
        return "55";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/home")
    public String login(Authentication authentication){
        User user = loginService.findByPhone(authentication.getName());
        System.out.println(user.getRole().getRole());
        String role = user.getRole().getRole();
        if (role.equals("学生")){
            return "index";
        }
        else if (role.equals("家长")){
            return role + "444";
        }
        else return "null";
    }

}
