package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.User;
import com.sise.familyEducation.location.GetPlaceByIp;
import com.sise.familyEducation.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

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

    public static String connectUser;

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
        User user = loginService.findUserByPhone(authentication.getName());
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
    public String login(Authentication authentication, HttpServletRequest request, HttpSession session) throws IOException, InterruptedException {
        connectUser = authentication.getName();
        Thread.sleep(500);
        User user = loginService.findUserByPhone(authentication.getName());
        String role = user.getRole().getRole();
        System.out.println(GetPlaceByIp.getPlace(request));
        Map<String, String> map = GetPlaceByIp.getPlace(request);
        String province = map.get("province");
        String city = map.get("city");
        session.setAttribute("province", province);
        session.setAttribute("city", city);
        session.setAttribute("authentication", authentication.getName());
        if (role.equals("学生")){
            return "student/student_home";
        }
        else if (role.equals("家长")){
            System.out.println(user.getRole().getRole());
            return "parent/parent_home";
        }
        else return "null";
    }

}