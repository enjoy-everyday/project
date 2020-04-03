package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.*;
import com.sise.familyEducation.service.*;
import com.sise.familyEducation.share.Share;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sise.familyEducation.share.Share.*;

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
//        int number = 1;
        User user = loginService.findUserByPhone(authentication.getName());
        Student student = studentService.findStudentByUser(user);
        List<Task> tasks = taskService.findTasksByStudentAndDisplay(student, true);
//        session.setAttribute("number", number);
        putCodeAndNumberInSession(1, 1, session);
        session.setAttribute("tasks", tasks);
        session.setAttribute("student", student);

        return "student/student_home";
    }

    /**
     * @date: 2020/2/19
     * @description: 查看被接受
     */

    @RequestMapping(value = "/accepted")
    public String accepted(Authentication authentication, HttpSession session){
//        int number = 2;
//        User user = loginService.findUserByPhone(authentication.getName());
//        Student student = studentService.findStudentByUser(user);
//        List<Task> tasks = taskService.findTasksByStudentAndResult(student, "接受");
//        session.setAttribute("number", number);
        findTasksByStudentAndResult(authentication, "接受", session);
        putCodeAndNumberInSession(1, 2, session);
//        session.setAttribute("tasks", tasks);
        return "student/student_home";
    }

     /**
      * @date: 2020/2/19
      * @description: 查看未处理
      */

    @RequestMapping(value = "/untreated")
    public String untreated(Authentication authentication, HttpSession session){
//        int number = 3;
//        User user = loginService.findUserByPhone(authentication.getName());
//        Student student = studentService.findStudentByUser(user);
//        List<Task> tasks = taskService.findTasksByStudentAndResult(student, "未处理");
//        session.setAttribute("number", number);
        findTasksByStudentAndResult(authentication, "未处理", session);
        putCodeAndNumberInSession(1, 3, session);
//        session.setAttribute("tasks", tasks);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/21
     * @description: 所有请家教内容
     */

    @RequestMapping(value = "/findAllApplicants")
    public String findAllApplicants(Authentication authentication, HttpSession session){
//        int code = 2;
        User user = loginService.findUserByPhone(authentication.getName());
        Student student = studentService.findStudentByUser(user);
        List<Detail> details = detailService.findNoApplicationDetailsByStudentId(student.getId());
        session.setAttribute("details", details);
        putCodeAndNumberInSession(2, -1, session);
//        session.setAttribute("code", code);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/23
     * @description: 搜索年级、科目
     */

    @RequestMapping(value = "/searchGradesAndSubjects")
    public String searchGradesAndSubjects(@RequestParam(value = "grade") String grade, @RequestParam(value = "subject") String subject, Authentication authentication, HttpSession session){
//        int code = 2;
        User user = loginService.findUserByPhone(authentication.getName());
        Student student = studentService.findStudentByUser(user);
        List<Detail> details;
//        System.out.println(grade);
//        System.out.println(subject);
        if (grade.equals("null") && !subject.equals("null")){
            details = detailService.findNoApplicationDetailsBySubject(student.getId(), subject);
        }
        else if (!grade.equals("null") && subject.equals("null")){
            details = detailService.findNoApplicationDetailsByGrade(student.getId(), grade);
        }
        else if (!grade.equals("null") && !subject.equals("null")){
            details = detailService.findNoApplicationDetailsByGradeAndSubject(student.getId(), grade, subject);
        }
        else {
            return "redirect:/findAllApplicants";
        }
//        session.setAttribute("code", code);
        session.setAttribute("details", details);
        putCodeAndNumberInSession(2, -1, session);
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
    @ResponseBody
    public String applyForTutor(@RequestParam(value = "detail_id") int id, Authentication authentication){
//        float score = 0;
        User user = loginService.findUserByPhone(authentication.getName());
        Student student = studentService.findStudentByUser(user);
        if (student.getName() == null){
            return "error";
        }
        else {
            Detail detail = detailService.findDetailById(id);
            Task task = new Task();
            task.setDate(new Date());
            task.setDetail(detail);
            task.setStudent(student);
            taskService.saveTask(task);
//            taskNumber = taskService.countTaskByStudent(student);
//            refuseRate = taskService.countTaskStudentAndResult(student, "拒绝") / taskNumber;
//            cancelRate = taskService.countTaskStudentAndResult(student, "取消") / taskNumber;
//            successRate = taskService.countTaskStudentAndResult(student, "成功") / taskNumber;
//            acceptRate = taskService.countTaskStudentAndResult(student, "接受") / taskNumber;
//            calculateStudentTotal(student);
//            score = ((-(refuseTime / taskNumber) + 1)  + (-(cancelTime / taskNumber) + 1) + (successTime / taskNumber) + (acceptTime / taskNumber)) * 100 / 80;
//            student.setScore(score);
//            studentService.saveStudent(student);
            updateStudentScore(student);
            return "success";
        }
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
     * @description: 学生取消应聘
     */

    @RequestMapping("/cancelTheApplication")
    @ResponseBody
    public String cancelTheApplication(@RequestParam(value = "task_id") int id, Authentication authentication, HttpSession session){
//        float score = 0;
        User user = loginService.findUserByPhone(authentication.getName());
        Student student = studentService.findStudentByUser(user);
        Task task = taskService.findTaskById(id);
        task.setResult("取消");
//        Message message = new Message();
//        message.setDate(new Date());
//        message.setMessage("取消");
//        message.setStudent(task.getStudent());
//        message.setParent(task.getDetail().getParent());
//        messageService.saveMessage(message);
        saveMessage(task.getDetail().getParent(), student, "取消");
        taskService.saveTask(task);
//        taskNumber = taskService.countTaskByStudent(student);
//        refuseRate = taskService.countTaskStudentAndResult(student, "拒绝") / taskNumber;
//        cancelRate = taskService.countTaskStudentAndResult(student, "取消") / taskNumber;
//        successRate = taskService.countTaskStudentAndResult(student, "成功") / taskNumber;
//        acceptRate = taskService.countTaskStudentAndResult(student, "接受") / taskNumber;
//        calculateStudentTotal(student);
//        score = ((-(refuseTime / taskNumber) + 1)  + (-(cancelTime / taskNumber) + 1) + (successTime / taskNumber) + (acceptTime / taskNumber)) * 100 / 80;
//        student.setScore(score);
//        studentService.saveStudent(student);
        updateStudentScore(student);
//        if (WebsocketConnectListener.bidiMap.get(task.getDetail().getParent().getPhone()) != null){
//            simpMessagingTemplate.convertAndSendToUser(task.getDetail().getParent().getPhone(), "/queue/getResponse", task.getStudent().getUsername() + "取消了编号为" + task.getDetail().getId() + "的应聘");
//        }
        sendWebsocketMessage(task.getDetail().getParent().getPhone(), task.getStudent().getUsername() + "取消了编号为" + task.getDetail().getId() + "的应聘");
        if ((int)session.getAttribute("number") == 2){
            return "accepted";
        }
        return "untreated";
    }

    /**
     * @date: 2020/3/22
     * @description: 删除应聘
     */

    @RequestMapping(value = "/deleteTheApplication")
    @ResponseBody
    public String deleteTheApplication(@RequestParam(value = "task_id") int id){
        Task task = taskService.findTaskById(id);
        if (task.getResult().equals("未处理") || task.getResult().equals("接受")){
            return "error";
        }
        else {
            task.setDisplay(false);
            taskService.saveTask(task);
            return "success";
        }
    }


}
