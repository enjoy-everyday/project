package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.entity.Student;
import com.sise.familyEducation.entity.Task;
import com.sise.familyEducation.entity.User;
import com.sise.familyEducation.service.DetailService;
import com.sise.familyEducation.service.LoginService;
import com.sise.familyEducation.service.StudentService;
import com.sise.familyEducation.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private DetailService detailService;
    @Autowired
    private TaskService taskService;

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
    public String findAllContent(Authentication authentication, HttpSession session){
        int code = 11;
        User user = loginService.findUserByPhone(authentication.getName());
        Student student = studentService.findStudentByUser(user);
        List<Detail> details = detailService.findNoApplicationDetailsByStudentId(student.getId());
        session.setAttribute("details", details);
        session.setAttribute("student", student);
        session.setAttribute("code", code);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/4
     * @description: 应聘家教
     */

    @RequestMapping("/applyForTutor")
    public String applyForTutor(@RequestParam(value = "detail_id") int id, Authentication authentication, HttpSession session){
        int code = 11;
        Detail detail = detailService.findDetailsById(id);
        User user = loginService.findUserByPhone(authentication.getName());
        Student student = studentService.findStudentByUser(user);
        Task task = new Task();
        task.setDate(new Date().toString());
        task.setDetail(detail);
        task.setStudent(student);
        taskService.saveTask(task);
        session.setAttribute("code", code);
        return "student/student_home";
    }

}
