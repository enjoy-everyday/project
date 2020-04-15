package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.*;
import com.sise.familyEducation.repository.DetailRepository;
import com.sise.familyEducation.service.*;
import com.sise.familyEducation.websocket.WebsocketConnectListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

import static com.sise.familyEducation.share.Share.*;

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
    private UserService userService;
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

    private String[] grades = {"小学一年级", "小学二年级", "小学三年级", "小学四年级", "小学五年级", "小学六年级", "", "初一", "初二", "初三", "高一", "高二", "高三"};

    /**
     * @date: 2020/2/3
     * @description: 请家教、重定向
     */

    @RequestMapping(value = "/please")
    public String please(@RequestParam(value = "id") int id, Authentication authentication){
        User user = loginService.findUserByPhone(authentication.getName());
        Parent parent = parentService.findParentByUser(user);
        User otherUser = userService.findUserById(id);
        Student student = studentService.findStudentByUser(user);
//        Message message = new Message();
//        message.setParent(parent);
//        message.setStudent(student);
//        message.setMessage("邀请");
//        message.setDate(new Date());
//        messageService.saveMessage(message);
        saveMessage(parent, student, "邀请");
//        if (WebsocketConnectListener.bidiMap.get(otherUser.getPhone()) != null){
//            simpMessagingTemplate.convertAndSendToUser(otherUser.getPhone(), "/queue/getResponse", parent.getUsername() + "邀请您去做家教");
//        }
        sendWebsocketMessage(otherUser.getPhone(), parent.getUsername() + "邀请您去做家教");
        return "redirect:/pleaseTutor";
    }

    /**
     * @date: 2020/2/3
     * @description: 跳转页面
     */

    @RequestMapping("/release")
    public String release(HttpSession session){
//        int code = 1;
//        session.setAttribute("code", code);
        putCodeAndNumberInSession(1, 0, session);
        return "parent/parent_home";
    }

    /**
     * @date: 2020/2/3
     * @description: 发布请家教内容
     */

//    @RequestMapping("/publishContent")
//    public String publishContent(Authentication authentication){
//        Parent parent = parentService.findParentByPhone(authentication.getName());
//        Detail detail = new Detail();
//        detail.setParent(parent);
//        detailService.saveDetail(detail);
//        return "parent/parent_home";
//    }

    /**
     * @date: 2020/2/5
     * @description: 查看应聘人员
     */

    @RequestMapping("/viewCandidates")
    public String viewCandidates(Authentication authentication, HttpSession session){
        User user = loginService.findUserByPhone(authentication.getName());
        Parent parent = parentService.findParentByUser(user);
//        int code = 2;
        session.setAttribute("parent", parent);
//        session.setAttribute("code", code);
        putCodeAndNumberInSession(2, -1, session);
        return "parent/parent_home";
    }

    /**
     * @date: 2020/2/5
     * @description: 家长接受，进入面试
     */

    @RequestMapping("/enterTheInterview")
    @ResponseBody
    public String enterYheInterview(@RequestParam(value = "task_id") int id, Authentication authentication){
//        float taskNumber = 0;
//        float refuseRate = 0;
//        float cancelRate = 0;
//        float successRate = 0;
//        float acceptRate = 0;
//        float score = 0;
        User user = loginService.findUserByPhone(authentication.getName());
        Parent parent = parentService.findParentByUser(user);
        Task task = updateTaskResult(id, "接受");
//        Task task = taskService.findTaskById(id);
//        task.setResult("接受");
//        Message message = new Message();
//        message.setDate(new Date());
//        message.setMessage("接受");
//        message.setStudent(task.getStudent());
//        message.setParent(task.getDetail().getParent());
//        messageService.saveMessage(message);
        saveMessage(task.getDetail().getParent(), task.getStudent(), "接受");
//        taskService.saveTask(task);
//        for (Detail detail : parent.getDetails()) {
//            taskNumber = taskNumber + taskService.countTaskByDetail(detail);
//            refuseRate = refuseRate + taskService.countTaskDetailAndResult(detail, "拒绝");
//            cancelRate = cancelRate + taskService.countTaskDetailAndResult(detail, "取消");
//            successRate = successRate + taskService.countTaskDetailAndResult(detail, "成功");
//            acceptRate = acceptRate + taskService.countTaskDetailAndResult(detail, "接受");
//        }
//        refuseRate = refuseRate / taskNumber;
//        cancelRate = cancelRate / taskNumber;
//        successRate = successRate / taskNumber;
//        acceptRate = acceptRate / taskNumber;
//        calculateParentTotal(parent);
//        score = ((-(refuseTime / taskNumber) + 1)  + (-(cancelTime / taskNumber) + 1) + (successTime / taskNumber) + (acceptTime / taskNumber)) * 100 / 80;
//        parent.setScore(score);
//        parentService.saveParent(parent);
        updateParentScore(parent);
//        if (WebsocketConnectListener.bidiMap.get(task.getStudent().getPhone()) != null){
//            simpMessagingTemplate.convertAndSendToUser(task.getStudent().getPhone(), "/queue/getResponse", task.getDetail().getParent().getUsername() + "接受了您的应聘");
//        }
        sendWebsocketMessage(task.getStudent().getPhone(), task.getDetail().getParent().getUsername() + "接受了您的应聘");
        return "success";
    }

    /**
     * @date: 2020/2/7
     * @description: 家长拒绝学生拒绝进入面试
     */

    @RequestMapping("/refuseEntry")
    @ResponseBody
    public String refuseEntry(@RequestParam(value = "task_id") int id, Authentication authentication){
//        float taskNumber = 0;
//        float refuseRate = 0;
//        float cancelRate = 0;
//        float successRate = 0;
//        float acceptRate = 0;
//        float score = 0;
        User user = loginService.findUserByPhone(authentication.getName());
        Parent parent = parentService.findParentByUser(user);
        Task task = updateTaskResult(id, "拒绝");
//        Task task = taskService.findTaskById(id);
//        task.setResult("拒绝");
//        Message message = new Message();
//        message.setDate(new Date());
//        message.setMessage("拒绝");
//        message.setStudent(task.getStudent());
//        message.setParent(task.getDetail().getParent());
//        messageService.saveMessage(message);
        saveMessage(task.getDetail().getParent(), task.getStudent(), "拒绝");
//        taskService.saveTask(task);
//        calculateParentTotal(parent);
//        score = ((-(refuseTime / taskNumber) + 1)  + (-(cancelTime / taskNumber) + 1) + (successTime / taskNumber) + (acceptTime / taskNumber)) * 100 / 80;
//        for (Detail detail : parent.getDetails()) {
//            taskNumber = taskNumber + taskService.countTaskByDetail(detail);
//            refuseRate = refuseRate + taskService.countTaskDetailAndResult(detail, "拒绝");
//            cancelRate = cancelRate + taskService.countTaskDetailAndResult(detail, "取消");
//            successRate = successRate + taskService.countTaskDetailAndResult(detail, "成功");
//            acceptRate = acceptRate + taskService.countTaskDetailAndResult(detail, "接受");
//        }
//        refuseRate = refuseRate / taskNumber;
//        cancelRate = cancelRate / taskNumber;
//        successRate = successRate / taskNumber;
//        acceptRate = acceptRate / taskNumber;
//        score = ((-(refuseRate) + 1)  + (-(cancelRate) + 1) + successRate + acceptRate) * 100 / 80;
//        parent.setScore(score);
//        parentService.saveParent(parent);
        updateParentScore(parent);
//        if (WebsocketConnectListener.bidiMap.get(task.getStudent().getPhone()) != null){
//            simpMessagingTemplate.convertAndSendToUser(task.getStudent().getPhone(), "/queue/getResponse", task.getDetail().getParent().getUsername() + "拒绝了您的应聘");
//        }
        sendWebsocketMessage(task.getStudent().getPhone(), task.getDetail().getParent().getUsername() + "拒绝了您的应聘");
        return "success";
    }

    /**
     * @date: 2020/2/25
     * @description: 查看所有学生
     */

    @RequestMapping(value = "/findAllStudents")
    public String findAllStudents(HttpSession session){
//        int code = 5;
        List<Student> students = studentService.findAllStudents();
//        session.setAttribute("code", code);
        session.setAttribute("students", students);
        putCodeAndNumberInSession(5, -1, session);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/25
     * @description: 查看所有发布
     */

    @RequestMapping(value = "/findAllPublish")
    public String findAllPublish(Authentication authentication, HttpSession session){
        findDetailsByDisplay(authentication, true, session);
        putCodeAndNumberInSession(1, 4, session);
//        int number = 4;
//        Parent parent = parentService.findParentByPhone(authentication.getName());
//        List<Detail> details = detailService.findDetailsByParentAndDisplay(parent, true);
//        session.setAttribute("number", number);
//        session.setAttribute("parent", parent);
//        session.setAttribute("details", details);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/25
     * @description: 查看已申请应聘
     */

    @RequestMapping(value = "/findApplied")
    public String findApplied(Authentication authentication, HttpSession session){
        findDetailsByDisplay(authentication, true, session);
        putCodeAndNumberInSession(1, 5, session);
//        int number = 5;
//        Parent parent = parentService.findParentByPhone(authentication.getName());
//        List<Detail> details = detailService.findDetailsByParentAndDisplay(parent, true);
//        session.setAttribute("number", number);
//        session.setAttribute("parent", parent);
//        session.setAttribute("details", details);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/25
     * @description: 查看已接受应聘
     */

    @RequestMapping(value = "/findAcceptedApplication")
    public String findAcceptedApplication(Authentication authentication, HttpSession session){
        findDetailsByDisplay(authentication, true, session);
        putCodeAndNumberInSession(1, 6, session);
//        int number = 6;
//        Parent parent = parentService.findParentByPhone(authentication.getName());
//        List<Detail> details = detailService.findDetailsByParentAndDisplay(parent, true);
//        session.setAttribute("number", number);
//        session.setAttribute("parent", parent);
//        session.setAttribute("details", details);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/26
     * @description: 发布
     */

    @RequestMapping(value = "/releaseDetails")
    @ResponseBody
    public String releaseDetails(@RequestParam(value = "json") String json, Authentication authentication){
        User user = loginService.findUserByPhone(authentication.getName());
        Parent parent = parentService.findParentByUser(user);
        if (parent.getName() == null)
            return "error";
        return saveDetail(json, parent);
//        else {
//            System.out.println("------------------" + json);
//            Map<String,String> map = new HashMap<>();
//            String string =json.replace("\"", "");
//            String[] array = string.split(";");
//            for(String str : array ){
//                String[] newArray = str.split(",");
//                System.out.println(str);
//                map.put(newArray[0], newArray[1]);
//            }
//            Map<String,String> map = stringConvertToMap(json);
//            String price = "";
//            String address = "";
//            if (map.get("calculation").equals("hours")){
//                price = map.get("price") + "元/小时"
//            }
//            else {
//                price = map.get("price") + "元/次";
//            }
//            if (!"null".equals(map.get("otherPlace"))){
//                address = parent.getAddress();
//            }
//            else {
//                address = map.get("otherPlace");
//            }
//            String[] gradeAndSubject = map.get("teachingGradeAndSubject").split("：");
//            Detail detail = new Detail();
//            detail.setDate(new Date());
//            detail.setParent(parent);
//            detail.setGender(map.get("gender").charAt(0));
//            detail.setQualification(map.get("qualification"));
//            detail.setExperience(map.get("experience"));
//            detail.setRequirement(map.get("otherRequirement"));
//            detail.setGrade(gradeAndSubject[0]);
//            detail.setSubject(gradeAndSubject[1]);
//            detail.setTeachingTime(map.get("teachingTime"));
//            detail.setPrice(price);
//            detail.setProvinceAndCity(parent.getProvinceAndCity());
//            detail.setAddress(address);
//            detailService.saveDetail(detail);
//            System.out.println("**************" + map);
//            return "success";
//        }
    }

    /**
     * @date: 2020/2/26
     * @description: 发布页面
     */

    @RequestMapping(value = "/releasePage")
    public String releasePage(HttpSession session){
        putCodeAndNumberInSession(1, 7, session);
//        int number = 7;
//        session.setAttribute("number", number);
        return "student/student_home";
    }

    /**
     * @date: 2020/3/14
     * @description: 发布时选择任教科目
     */

    @RequestMapping(value = "/choiceTeachingGradeAndSubject")
    @ResponseBody
    public String choiceTeachingGradeAndSubject(@RequestParam(value = "array") String[] array, @RequestParam(value = "gradeNumber")int gradeNumber){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++){
            if (!array[i].equals("")){
                list.add(array[i]);
            }
        }
        if (list.size() == 0)
            return "null";
        return numberOfSubjects(gradeNumber, list);
//        }
//        else {
//            String replace = list.toString().replace("[", "").replace("]", "");
//            if (gradeNumber < 6){
//                if (list.size() == 3){
//                    result = grades[gradeNumber] + "：全部科目<br>";
//                }
//                else {
//                    result = grades[gradeNumber] + "：" + replace + "<br>";
//                }
//            }
//            else {
//                if (list.size() == 9){
//                    result = grades[gradeNumber - 1] + "：全部科目<br>";
//                }
//                else {
//                    result = grades[gradeNumber - 1] + "：" + replace + "<br>";
//                }
//            }
//            return result;
//        }
    }

    /**
     * @date: 2020/3/22
     * @description: 删除已发布
     */

    @RequestMapping(value = "/deleteDetail")
    @ResponseBody
    public String deleteDetail(@RequestParam(value = "detail_id") int id){
        Detail detail = detailService.findDetailById(id);
        detail.setDisplay(false);
        detailService.saveDetail(detail);
        return "success";
    }

    /**
     * @date: 2020/3/23
     * @description: 通过学历和评分筛选学生
     */

    @RequestMapping(value = "/searchQualificationAndScore")
    public String searchQualificationAndScore(@RequestParam(value = "qualification") String qualification, @RequestParam(value = "score") float score, HttpSession session){
//        int code = 5;
//        List<Student> students;
//        switch (qualification) {
//            case "博士": {
//                students = studentService.findStudentsByQualificationAndScore(qualification, score);
//                break;
//            }
//            case "硕士": {
//                students = studentService.findStudentsByQualificationLikeAndScore(score);
//                break;
//            }
//            case "本科": {
//                students = studentService.findStudentsByQualificationNotAndScore("大专", score);
//                break;
//            }
//            default: {
//                students = studentService.findStudentsByQualificationNotAndScore(qualification, score);
//                break;
//            }
//        }
//        List<Student> students = getStudentsByQualificationAndScore(qualification, score);
        session.setAttribute("students", getStudentsByQualificationAndScore(qualification, score));
        putCodeAndNumberInSession(5, -1, session);
//        session.setAttribute("code", code);
        return "student/student_home";
    }

    /**
     * @date: 2020/3/26
     * @description: 请家教
     */

    @RequestMapping(value = "/pleaseTutor")
    public String pleaseTutor(){
        return "/findAllStudents";
    }

    /**
     * @date: 2020/4/13
     * @description: 获得查询学生集合
     */

    private List<Student> getStudentsByQualificationAndScore(String qualification, float score){
        if (qualification.equals("博士"))
            return studentService.findStudentsByQualificationAndScore(qualification, score);
        if (qualification.equals("硕士"))
            return studentService.findStudentsByQualificationLikeAndScore(score);
        if (qualification.equals("本科"))
            return studentService.findStudentsByQualificationNotAndScore("大专", score);
        return studentService.findStudentsByQualificationNotAndScore(qualification, score);
    }

    /**
     * @date: 2020/4/13
     * @description: 判断是否选择全部科目
     */

    private String selectAllAccounts(int gradeNumber, List<String> list, int maxListSize){
        if (list.size() == maxListSize)
            return grades[gradeNumber] + "：全部科目<br>";
        return grades[gradeNumber] + "：" + list.toString().replace("[", "").replace("]", "") + "<br>";
    }

    /**
     * @date: 2020/4/13
     * @description: 判断总科目数
     */

    private String numberOfSubjects(int gradeNumber, List<String> list){
        if (gradeNumber < 6)
            return selectAllAccounts(gradeNumber, list, 3);
        return selectAllAccounts(gradeNumber, list, 9);
    }

    /**
     * @date: 2020/4/13
     * @description: 计算价格的规则
     */

    private String priceRules(Map<String,String> map){
        if (map.get("calculation").equals("hours"))
            return map.get("price") + "元/小时";
        return map.get("price") + "元/次";
    }

    /**
     * @date: 2020/4/13
     * @description: 任教地点选择
     */

    private String teachingPlace(Map<String,String> map, Parent parent){
        if (!"null".equals(map.get("otherPlace")))
            return parent.getAddress();
        return map.get("otherPlace");
    }

    /**
     * @date: 2020/4/13
     * @description: 将招聘信息保存到数据库
     */

    private String saveDetail(String json, Parent parent){
        Map<String, String> map = stringConvertToMap(json);
        String[] gradeAndSubject = map.get("teachingGradeAndSubject").split("：");
        Detail detail = new Detail();
        detail.setDate(new Date());
        detail.setParent(parent);
        detail.setGender(map.get("gender").charAt(0));
        detail.setQualification(map.get("qualification"));
        detail.setExperience(map.get("experience"));
        detail.setRequirement(map.get("otherRequirement"));
        detail.setGrade(gradeAndSubject[0]);
        detail.setSubject(gradeAndSubject[1]);
        detail.setTeachingTime(map.get("teachingTime"));
        detail.setPrice(priceRules(map));
        detail.setProvinceAndCity(parent.getProvinceAndCity());
        detail.setAddress(teachingPlace(map, parent));
        detailService.saveDetail(detail);
        return "success";
    }







}
