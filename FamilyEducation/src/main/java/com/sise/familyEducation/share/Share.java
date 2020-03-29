package com.sise.familyEducation.share;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.entity.Parent;
import com.sise.familyEducation.entity.Student;
import com.sise.familyEducation.entity.User;
import com.sise.familyEducation.service.*;
import com.sise.familyEducation.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

/**
 * @program: FamilyEducation
 * @description: 公用类
 * @author: wxy
 * @create: 2020-03-28 13:07
 **/

public class Share {

    private static UserService userService = BeanUtils.getBean(UserService.class);
    private static BasicService basicService = BeanUtils.getBean(BasicService.class);
    private static LoginService loginService = BeanUtils.getBean(LoginService.class);
    private static DetailService detailService = BeanUtils.getBean(DetailService.class);
    private static TaskService taskService = BeanUtils.getBean(TaskService.class);
    private static ParentService parentService = BeanUtils.getBean(ParentService.class);
    private static StudentService studentService = BeanUtils.getBean(StudentService.class);
    private static MessageService messageService = BeanUtils.getBean(MessageService.class);

    public static float taskNumber = 0;
    public static float refuseTime = 0;
    public static float cancelTime = 0;
    public static float successTime = 0;
    public static float acceptTime = 0;
    public static float score = 0;

    public static void calculateStudentRating(Student student){
        taskNumber = taskService.countTaskByStudent(student);
        score = student.getScore();
        refuseTime = taskService.countTaskStudentAndResult(student, "拒绝");
        cancelTime = taskService.countTaskStudentAndResult(student, "取消");
        successTime = taskService.countTaskStudentAndResult(student, "成功");
        acceptTime = taskService.countTaskStudentAndResult(student, "接受");
    }

    public static void calculateParentRating(Parent parent){
        score = parent.getScore();
        for (Detail detail : parent.getDetails()){
            taskNumber = taskNumber + taskService.countTaskByDetail(detail);
            refuseTime = refuseTime + taskService.countTaskDetailAndResult(detail, "拒绝");
            cancelTime = cancelTime + taskService.countTaskDetailAndResult(detail, "取消");
            successTime = successTime + taskService.countTaskDetailAndResult(detail, "成功");
            acceptTime = acceptTime + taskService.countTaskDetailAndResult(detail, "接受");
        }
    }

    public static void putAboutTaskInSession(HttpSession session){
        session.setAttribute("score", score);
        session.setAttribute("taskNumber", taskNumber);
        session.setAttribute("refuseTime", refuseTime);
        session.setAttribute("cancelTime", cancelTime);
        session.setAttribute("successTime", successTime);
        session.setAttribute("acceptTime", acceptTime);
    }

}
