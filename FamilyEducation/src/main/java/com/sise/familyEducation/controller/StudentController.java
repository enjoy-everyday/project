package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.service.LoginService;
import com.sise.familyEducation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 学生
 * @author: wxy
 * @create: 2020-02-03 21:16
 **/

@Controller
public class StudentController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private StudentService studentService;

    /**
     * @date: 2020/2/3
     * @description: 请求转发
     */

    /**
     * @date: 2020/2/3
     * @description: 跳转页面
     */

    /**
     * @date: 2020/2/3
     * @description: 查看请家教内容
     */

    @RequestMapping("/findAllContent")
    public String findAllContent(HttpSession session){
        int code = 11;
        List<Detail> details = studentService.findAll();
        System.out.println(details.size());
        session.setAttribute("details", details);
        session.setAttribute("code", code);
        return "student/student_home";
    }

}
