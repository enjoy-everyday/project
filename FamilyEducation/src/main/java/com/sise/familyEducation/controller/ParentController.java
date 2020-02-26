package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.*;
import com.sise.familyEducation.repository.DetailRepository;
import com.sise.familyEducation.service.*;
import com.sise.familyEducation.websocket.WebsocketConnectListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
    private TaskService taskService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private DetailService detailService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
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
        Parent parent = parentService.findParentByPhone(authentication.getName());
        Detail detail = new Detail();
        detail.setParent(parent);
        detailService.saveDetail(detail);
        return "parent/parent_home";
    }

    /**
     * @date: 2020/2/5
     * @description: 查看应聘人员
     */

    @RequestMapping("/viewCandidates")
    public String viewCandidates(Authentication authentication, HttpSession session){
        Parent parent = parentService.findParentByPhone(authentication.getName());
        int code = 2;
        session.setAttribute("parent", parent);
        session.setAttribute("code", code);
        return "parent/parent_home";
    }

    /**
     * @date: 2020/2/5
     * @description: 通过，进入面试
     */

    @RequestMapping("/enterTheInterview")
    public String enterYheInterview(@RequestParam(value = "task_id") int id){
        Task task = taskService.findTaskById(id);
        task.setResult("接受");
        if (WebsocketConnectListener.bidiMap.get(task.getStudent().getPhone()) != null){
            simpMessagingTemplate.convertAndSendToUser(task.getStudent().getPhone(), "/queue/getResponse", task.getDetail().getParent().getUsername() + "接受了您的应聘");
        }
        Message message = new Message();
        message.setDate(new Date().toString());
        message.setMessage("接受");
        message.setStudent(task.getStudent());
        message.setParent(task.getDetail().getParent());
        messageService.saveMessage(message);
        taskService.saveTask(task);
        return "redirect:/viewCandidates";
    }

    /**
     * @date: 2020/2/7
     * @description: 没有通过，拒绝进入面试
     */

    @RequestMapping("/refuseEntry")
    public String refuseEntry(@RequestParam(value = "task_id") int id){
        Task task = taskService.findTaskById(id);
        task.setResult("拒绝");
        if (WebsocketConnectListener.bidiMap.get(task.getStudent().getPhone()) != null){
            simpMessagingTemplate.convertAndSendToUser(task.getStudent().getPhone(), "/queue/getResponse", task.getDetail().getParent().getUsername() + "拒绝了您的应聘");
        }
        Message message = new Message();
        message.setDate(new Date().toString());
        message.setMessage("拒绝");
        message.setStudent(task.getStudent());
        message.setParent(task.getDetail().getParent());
        messageService.saveMessage(message);
        taskService.saveTask(task);
        return "redirect:/viewCandidates";
    }

    /**
     * @date: 2020/2/25
     * @description: 查看所有学生
     */

    @RequestMapping(value = "/findAllStudents")
    public String findAllStudents(HttpSession session){
        int code = 5;
        List<Student> students = studentService.findAllStudents();
        session.setAttribute("code", code);
        session.setAttribute("students", students);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/25
     * @description: 查看所有发布
     */

    @RequestMapping(value = "/findAllPublish")
    public String findAllPublish(Authentication authentication, HttpSession session){
        int number = 4;
        Parent parent = parentService.findParentByPhone(authentication.getName());
        session.setAttribute("number", number);
        session.setAttribute("parent", parent);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/25
     * @description: 查看已申请应聘
     */

    @RequestMapping(value = "/findApplied")
    public String findApplied(Authentication authentication, HttpSession session){
        int number = 5;
        Parent parent = parentService.findParentByPhone(authentication.getName());
        session.setAttribute("number", number);
        session.setAttribute("parent", parent);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/25
     * @description: 查看已接受应聘
     */

    @RequestMapping(value = "/findAcceptedApplication")
    public String findAcceptedApplication(Authentication authentication, HttpSession session){
        int number = 6;
        Parent parent = parentService.findParentByPhone(authentication.getName());
        session.setAttribute("number", number);
        session.setAttribute("parent", parent);
        return "student/student_home";
    }



}
