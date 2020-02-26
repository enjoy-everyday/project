package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.*;
import com.sise.familyEducation.service.*;
import com.sise.familyEducation.websocket.WebsocketConnectListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private MessageService messageService;
    @Autowired
    private HistoricalTaskService historicalTaskService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
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
     * @date: 2020/2/19
     * @description: 查看全部应聘
     */

    @RequestMapping(value = "/viewAll")
    public String viewAll(Authentication authentication, HttpSession session){
        int number = 1;
        User user = loginService.findUserByPhone(authentication.getName());
        Student student = studentService.findStudentByUser(user);
        session.setAttribute("number", number);
        session.setAttribute("student", student);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/19
     * @description: 查看被接受
     */

    @RequestMapping(value = "/accepted")
    public String accepted(Authentication authentication, HttpSession session){
        int number = 2;
        User user = loginService.findUserByPhone(authentication.getName());
        Student student = studentService.findStudentByUser(user);
        List<Task> tasks = taskService.findTasksByStudentAndResult(student, "接受");
        session.setAttribute("number", number);
        session.setAttribute("tasks", tasks);
        return "student/student_home";
    }

     /**
      * @date: 2020/2/19
      * @description: 查看未处理
      */

    @RequestMapping(value = "/untreated")
    public String untreated(Authentication authentication, HttpSession session){
        int number = 3;
        User user = loginService.findUserByPhone(authentication.getName());
        Student student = studentService.findStudentByUser(user);
        List<Task> tasks = taskService.findTasksByStudentAndResult(student, "未处理");
        session.setAttribute("number", number);
        session.setAttribute("tasks", tasks);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/21
     * @description: 所有请家教内容
     */

    @RequestMapping(value = "/findAllApplicants")
    public String findAllApplicants(Authentication authentication, HttpSession session){
        int code = 2;
        User user = loginService.findUserByPhone(authentication.getName());
        Student student = studentService.findStudentByUser(user);
        List<Detail> details = detailService.findNoApplicationDetailsByStudentId(student.getId());
        session.setAttribute("details", details);
        session.setAttribute("code", code);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/23
     * @description: 搜索年级、科目
     */

    @RequestMapping(value = "/searchGradesAndSubjects")
    public String searchGradesAndSubjects(@RequestParam(value = "grade") String grade, @RequestParam(value = "subject") String subject, Authentication authentication, HttpSession session){
        int code = 2;
        User user = loginService.findUserByPhone(authentication.getName());
        Student student = studentService.findStudentByUser(user);
        System.out.println(grade);
        System.out.println(subject);
        if (grade.equals("null") && !subject.equals("null")){
            List<Detail> details = detailService.findNoApplicationDetailsBySubject(student.getId(), subject);
            session.setAttribute("details", details);
        }
        else if (!grade.equals("null") && subject.equals("null")){
            List<Detail> details = detailService.findNoApplicationDetailsByGrade(student.getId(), grade);
            session.setAttribute("details", details);
        }
        else if (!grade.equals("null") && !subject.equals("null")){
            List<Detail> details = detailService.findNoApplicationDetailsByGradeAndSubject(student.getId(), grade, subject);
            session.setAttribute("details", details);
        }
        else {
            return "redirect:/findAllApplicants";
        }
        session.setAttribute("code", code);
        return "student/student_home";
    }



    /**
     * @date: 2020/2/3
     * @description: 查看请家教内容
     */

//    @RequestMapping("/findAllContent")
//    public String findAllContent(Authentication authentication, HttpSession session){
//        int code = 11;
//        User user = loginService.findUserByPhone(authentication.getName());
//        Student student = studentService.findStudentByUser(user);
//        List<Detail> details = detailService.findNoApplicationDetailsByStudentId(student.getId());
//        session.setAttribute("details", details);
//        session.setAttribute("student", student);
//        session.setAttribute("code", code);
//        return "student/student_home";
//    }

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
        return "redirect:/allApplicants";
    }

    /**
     * @date: 2020/2/6
     * @description: 查看应聘结果
     */

//    @RequestMapping("/findApplicationResult")
//    public String findApplicationResult(Authentication authentication, HttpSession session){
//        int code = 12;
//        int num = 0;
//        User user = loginService.findUserByPhone(authentication.getName());
//        Student student = studentService.findStudentByUser(user);
//        for (Task task : student.getTasks()){
//            if (task.getWhetherToPass().equals("是")){
//                num++;
//            }
//        }
//        session.setAttribute("code", code);
//        session.setAttribute("num", num);
//        session.setAttribute("student", student);
//        return "student/student_home";
//    }


    /**
     * @date: 2020/2/21
     * @description: 学生取消
     */

    @RequestMapping("/cancelTheApplication")
    public String cancelTheApplication(@RequestParam(value = "task_id") int id, HttpSession session){
        Task task = taskService.findTaskById(id);
        task.setResult("取消");
        if (WebsocketConnectListener.bidiMap.get(task.getDetail().getParent().getPhone()) != null){
            simpMessagingTemplate.convertAndSendToUser(task.getDetail().getParent().getPhone(), "/queue/getResponse", task.getStudent().getUsername() + "取消了编号为" + task.getDetail().getId() + "的应聘");
        }
        Message message = new Message();
        message.setDate(new Date().toString());
        message.setMessage("取消");
        message.setStudent(task.getStudent());
        message.setParent(task.getDetail().getParent());
        messageService.saveMessage(message);
        taskService.saveTask(task);
        if ((int)session.getAttribute("number") == 2){
            return "redirect:/accepted";
        }
        return "redirect:/untreated";
    }

}
