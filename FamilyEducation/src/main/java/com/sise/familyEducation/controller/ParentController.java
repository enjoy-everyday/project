package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.entity.User;
import com.sise.familyEducation.service.LoginService;
import com.sise.familyEducation.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 家长部分
 * @author: wxy
 * @create: 2020-02-03 17:28
 **/

@Controller
public class ParentController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private ParentService parentService;

    /**
     * @date: 2020/2/3
     * @description: 请求转发
     */

    /**
     * @date: 2020/2/3
     * @description: 跳转页面
     */

    @RequestMapping("/release")
    public String release(HttpSession session){
        int code = 1;
        session.setAttribute("code", code);
        return "parent/parent_home";
    }

    /**
     * @date: 2020/2/3
     * @description: 发布请家教内容
     */

    @RequestMapping("/publishContent")
    public String publishContent(Authentication authentication){
        List<User> users = new ArrayList<>();
        User user = loginService.findByPhone(authentication.getName());
        users.add(user);
        Detail detail = new Detail();
        detail.setUsers(users);
        parentService.save(detail);
        return "parent/parent_home";
    }

}
