package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.*;
import com.sise.familyEducation.location.GetPlaceByIp;
import com.sise.familyEducation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
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
    private RoleService roleService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private DetailService detailService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MessageService messageService;

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
        int code = 0;
        User user = loginService.findUserByPhone(authentication.getName());
        String role = user.getRole().getRole();
        System.out.println(GetPlaceByIp.getPlace(request));
        Map<String, String> map = GetPlaceByIp.getPlace(request);
        String province = map.get("province");
        String city = map.get("city");
        session.setAttribute("province", province);
        session.setAttribute("city", city);
        session.setAttribute("authentication", authentication.getName());
        session.setAttribute("code", code);
        session.setAttribute("role", role);
        if (role.equals("学生")){
            Student person = studentService.findStudentByUser(user);
            List<Detail> details = detailService.findDetailsByAddressLike(province + city);
            int messageNumber = messageService.countByStudentAndState(person, false);
            session.setAttribute("messageNumber", messageNumber);
            session.setAttribute("person", person);
            session.setAttribute("details", details);
        }
        else if (role.equals("家长")){
            Parent person = parentService.findParentByPhone(authentication.getName());
            int messageNumber = messageService.countByParentAndState(person, false);
            session.setAttribute("messageNumber", messageNumber);
            session.setAttribute("person", person);
        }

        return "student/student_home";
    }

    /**
     * @date: 2020/3/5
     * @description: 注册
     */

    @RequestMapping(value = "/register")
    public String register(@RequestParam(value = "phone") String phone, @RequestParam(value = "password") String pw, @RequestParam(value = "role") String r){
        String password = new BCryptPasswordEncoder().encode(pw);
        Role role = roleService.findRoleByRole(r);
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setRole(role);

        return "/login";
    }


}
