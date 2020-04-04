package com.sise.familyEducation.share;

import com.sise.familyEducation.entity.*;
import com.sise.familyEducation.service.*;
import com.sise.familyEducation.utils.BeanUtils;
import com.sise.familyEducation.websocket.WebsocketConnectListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpSession;
import java.util.*;

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
    private static SimpMessagingTemplate simpMessagingTemplate = BeanUtils.getBean(SimpMessagingTemplate.class);

    public static float taskNumber = 0;
    public static float refuseTime = 0;
    public static float cancelTime = 0;
    public static float successTime = 0;
    public static float acceptTime = 0;
    public static float refuseRate = 0;
    public static float cancelRate = 0;
    public static float successRate = 0;
    public static float acceptRate = 0;
    public static float score = 0;

    /**
     * @date: 2020/3/29
     * @description: 计算学生应聘、被拒绝、取消、成功、被接受等的总数
     */

    public static void calculateStudentTotal(Student student){
        taskNumber = taskService.countTaskByStudent(student);
        refuseTime = taskService.countTaskStudentAndResult(student, "拒绝");
        cancelTime = taskService.countTaskStudentAndResult(student, "取消");
        successTime = taskService.countTaskStudentAndResult(student, "成功");
        acceptTime = taskService.countTaskStudentAndResult(student, "接受");
    }

    /**
     * @date: 2020/3/29
     * @description: 计算家长被应聘、拒绝、被取消、成功、接受等的总数
     */

    public static void calculateParentTotal(Parent parent){
        for (Detail detail : parent.getDetails()){
            taskNumber = taskNumber + taskService.countTaskByDetail(detail);
            refuseTime = refuseTime + taskService.countTaskDetailAndResult(detail, "拒绝");
            cancelTime = cancelTime + taskService.countTaskDetailAndResult(detail, "取消");
            successTime = successTime + taskService.countTaskDetailAndResult(detail, "成功");
            acceptTime = acceptTime + taskService.countTaskDetailAndResult(detail, "接受");
        }
    }

    /**
     * @date: 2020/4/3
     * @description: 计算应聘率，拒绝率等等
     */

    public static void calculateRate(){
        refuseRate = ((float)((int)(refuseTime / taskNumber * 1000))) / 1000;
        cancelRate = ((float)((int)(cancelTime / taskNumber * 1000))) / 1000;
        successRate = ((float)((int)(successTime / taskNumber * 1000))) / 1000;
        acceptRate = ((float)((int)(acceptTime / taskNumber * 1000))) / 1000;
    }

    /**
     * @date: 2020/3/29
     * @description: 得到学生评分并存入session
     */

    public static void getStudentScore(Student student, HttpSession session){
        updateStudentScore(student);
        putAboutTaskInSession(session);
    }

    /**
     * @date: 2020/3/29
     * @description: 得到家长评分并存入session
     */

    public static void getParentScore(Parent parent, HttpSession session){
        updateParentScore(parent);
        putAboutTaskInSession(session);
    }

    /**
     * @date: 2020/3/29
     * @description: 更新学生评分
     */

    public static void updateStudentScore(Student student){
        calculateStudentTotal(student);
        score = ((-(refuseTime / taskNumber) + 1)  + (-(cancelTime / taskNumber) + 1) + (successTime / taskNumber) + (acceptTime / taskNumber)) * 100 / 80;
        student.setScore(((float)((int)(score * 100))) / 100);
        studentService.saveStudent(student);
    }

    /**
     * @date: 2020/3/29
     * @description: 更新家长评分
     */

    public static void updateParentScore(Parent parent){
        calculateParentTotal(parent);
        score = ((-(refuseTime / taskNumber) + 1)  + (-(cancelTime / taskNumber) + 1) + (successTime / taskNumber) + (acceptTime / taskNumber)) * 100 / 80;
        parent.setScore(((float)((int)(score * 100))) / 100);
        parentService.saveParent(parent);
    }

    /**
     * @date: 2020/3/29
     * @description: 对某个用户发送信息
     */

    public static void sendWebsocketMessage(String phone, String message){
        if (WebsocketConnectListener.bidiMap.get(phone) != null){
            simpMessagingTemplate.convertAndSendToUser(phone, "/queue/getResponse", message);
        }
    }


    /**
     * @date: 2020/3/29
     * @description: 数组转换为Map
     */

    public static Map<Integer, List<String>> arrayConvertToMap(String array[][]){
        Map<Integer, List<String>> map = new HashMap<>();
        for (int i = 0; i < array.length; i++){
            List<String> list = new ArrayList<>();
            if (array[i].length != 0){
                for (int j = 0; j < array[i].length; j++){
                    if (map.get(i) == null && !array[i][j].equals("")){
                        list.add(array[i][j]);
                        map.put(i, list);
                    }
                    else if(!array[i][j].equals("")) {
                        map.get(i).add(array[i][j]);
                    }
                }
            }
        }
        return map;
    }

    /**
     * @date: 2020/3/29
     * @description: 字符串转换为Map
     */

    public static Map<String, String> stringConvertToMap(String converted){
        Map<String, String> map = new HashMap<>();
        String string =converted.replace("\"", "");
        String[] array = string.split(";");
        for(String str : array ){
            String[] newArray = str.split(",");
            System.out.println(str);
            map.put(newArray[0], newArray[1]);
        }
        return map;
    }

    /**
     * @date: 2020/3/29
     * @description: 通过结果查找Detail
     */

    public static void findDetailsByDisplay(Authentication authentication, boolean display, HttpSession session){
        Parent parent = parentService.findParentByPhone(authentication.getName());
        List<Detail> details = detailService.findDetailsByParentAndDisplay(parent, display);
        session.setAttribute("parent", parent);
        session.setAttribute("details", details);
    }

    /**
     * @date: 2020/3/29
     * @description: 保存通知信息
     */

    public static void saveMessage(Parent parent, Student student, String msg){
        Message message = new Message();
        message.setParent(parent);
        message.setStudent(student);
        message.setMessage(msg);
        message.setDate(new Date());
        messageService.saveMessage(message);
    }

    /**
     * @date: 2020/3/30
     * @description: 通过得到学生应聘结果
     */

    public static void findTasksByStudentAndResult(Authentication authentication, String result, HttpSession session){
        User user = loginService.findUserByPhone(authentication.getName());
        Student student = studentService.findStudentByUser(user);
        List<Task> tasks = taskService.findTasksByStudentAndResult(student, result);
        session.setAttribute("tasks", tasks);
    }

    /**
     * @date: 2020/3/29
     * @description: 存入code和number
     */

    public static void putCodeAndNumberInSession(int code, int number, HttpSession session){;
        session.setAttribute("code", code);;
        session.setAttribute("number", number);
    }


    /**
     * @date: 2020/3/29
     * @description: 存入关于task的session
     */

    public static void putAboutTaskInSession(HttpSession session){
        session.setAttribute("score", score);
        session.setAttribute("taskNumber", taskNumber);
        session.setAttribute("refuseTime", refuseTime);
        session.setAttribute("cancelTime", cancelTime);
        session.setAttribute("successTime", successTime);
        session.setAttribute("acceptTime", acceptTime);
    }

    /**
     * @date: 2020/4/3
     * @description: 存入关于应聘率等的session
     */

    public static void putRateInSession(HttpSession session){
        session.setAttribute("taskNumber", taskNumber);
        session.setAttribute("refuseRate", refuseRate);
        session.setAttribute("cancelRate", cancelRate);
        session.setAttribute("successRate", successRate);
        session.setAttribute("acceptRate", acceptRate);
    }


}
