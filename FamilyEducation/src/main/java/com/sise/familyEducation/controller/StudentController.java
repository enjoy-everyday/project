package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.*;
import com.sise.familyEducation.service.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
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
    private TaskService taskService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private DetailService detailService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private HistoricalTaskService historicalTaskService;
    @Autowired
    private HistoricalDetailService historicalDetailService;

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
        return "redirect:/findAllContent";
    }

    /**
     * @date: 2020/2/6
     * @description: 查看应聘结果
     */

    @RequestMapping("/findApplicationResult")
    public String findApplicationResult(Authentication authentication, HttpSession session){
        int code = 12;
        int number = 0;
        User user = loginService.findUserByPhone(authentication.getName());
        Student student = studentService.findStudentByUser(user);
        for (Task task : student.getTasks()){
            if (task.getWhetherToPass().equals("是")){
                number++;
            }
        }
        session.setAttribute("code", code);
        session.setAttribute("number", number);
        session.setAttribute("student", student);
        return "student/student_home";
    }

    @RequestMapping("/cancelTheApplication")
    public String cancelTheApplication(@RequestParam(value = "task_id") int id){
        Task task = taskService.findTaskById(id);
        if (task.getHistoricalDetail() == null){
            HistoricalDetail historicalDetail = new HistoricalDetail();
            historicalDetail.setDate(new Date().toString());
            historicalDetail.setContext(task.getDetail().getContext());
            historicalDetail.setParent(task.getDetail().getParent());
            historicalDetailService.saveHistoricalDetail(historicalDetail);
            for (Task task1 : task.getDetail().getTasks()){
                task1.setHistoricalDetail(historicalDetail);
            }
        }
        HistoricalTask historicalTask = new HistoricalTask();
        historicalTask.setDate(new Date().toString());
        historicalTask.setResult("取消");
        historicalTask.setHistoricalDetail(task.getHistoricalDetail());
        historicalTask.setStudent(task.getStudent());
        historicalTaskService.saveHistoricalTask(historicalTask);
        taskService.deleteTaskById(id);
        return "redirect:/findApplicationResult";
    }

}
