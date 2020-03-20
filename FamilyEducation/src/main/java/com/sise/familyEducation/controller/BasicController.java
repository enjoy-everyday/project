package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.*;
import com.sise.familyEducation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @program: FamilyEducation
 * @description: 基础
 * @author: wxy
 * @create: 2020-02-08 21:17
 **/

@Controller
public class BasicController {

    @Autowired
    private UserService userService;
    @Autowired
    private BasicService basicService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private DetailService detailService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MessageService messageService;

    /**
     * @date: 2020/3/19
     * @description: 查看其它人的个人资料及介绍、重定向
     */

    @RequestMapping(value = "/introductionPage", method = RequestMethod.GET)
    public String introductionPage(@RequestParam(value = "id") int id, HttpSession session){
        int taskNumber = 0;
        int refuseTime = 0;
        int cancelTime = 0;
        int successTime = 0;
        int acceptTime = 0;
        int score = 0;
        long allDetailNumber = detailService.countAllDetail();
        User otherUser = userService.findUserById(id);
        String role = otherUser.getRole().getRole();
        if (role.equals("学生")){
            Student otherStudent = studentService.findStudentByUser(otherUser);
            taskNumber = taskService.countTaskByStudent(otherStudent);
            refuseTime = taskService.countTaskStudentAndResult(otherStudent, "拒绝");
            cancelTime = taskService.countTaskStudentAndResult(otherStudent, "取消");
            successTime = taskService.countTaskStudentAndResult(otherStudent, "成功");
            acceptTime = taskService.countTaskStudentAndResult(otherStudent, "接受");
            if (taskNumber != 0){
                score = ((-(refuseTime / taskNumber) + 1)  + (-(cancelTime / taskNumber) + 1) + (successTime / taskNumber) + (acceptTime / taskNumber)) * 5;
            }
            session.setAttribute("otherUser", otherStudent);
        }
        else {
            Parent otherParent = parentService.findParentByUser(otherUser);
            for (Detail detail : otherParent.getDetails()) {
                taskNumber = taskNumber + taskService.countTaskByDetail(detail);
                refuseTime = taskService.countTaskDetailAndResult(detail, "拒绝");
                cancelTime = taskService.countTaskDetailAndResult(detail, "取消");
                successTime = taskService.countTaskDetailAndResult(detail, "成功");
                acceptTime = taskService.countTaskDetailAndResult(detail, "接受");
                if (taskNumber != 0) {
                    score = ((-(refuseTime / taskNumber) + 1) + (-(cancelTime / taskNumber) + 1) + (successTime / taskNumber) + (acceptTime / taskNumber)) * 5;
                }
                session.setAttribute("otherUser", otherParent);
            }
        }
        session.setAttribute("taskNumber", taskNumber);
        session.setAttribute("refuseTime", refuseTime);
        session.setAttribute("cancelTime", cancelTime);
        session.setAttribute("successTime", successTime);
        session.setAttribute("acceptTime", acceptTime);
        session.setAttribute("score", score);
        session.setAttribute("allDetailNumber", allDetailNumber);
        return "redirect:/viewIntroduction";
    }

    /**
     * @date: 2020/3/19
     * @description: 获取省份
     */

    @RequestMapping(value = "/getProvince")
    @ResponseBody
    public List<Province> getAllProvinces(){
        List<Province> provinces = basicService.findAllProvince();
        return provinces;
    }

    /**
     * @date: 2020/3/19
     * @description: 获取城市
     */

    @RequestMapping(value = "/getCities")
    @ResponseBody
    public List<City> getCities(@RequestParam(value = "province_code") int province_code){
        List<City> cities = basicService.findProvinceById(province_code).getCities();
        return cities;
    }

    /**
     * @date: 2020/3/19
     * @description: 获取区
     */

    @RequestMapping(value = "/getAreas")
    @ResponseBody
    public List<Area> getAreas(@RequestParam(value = "city_code") int city_code){
        List<Area> areas = basicService.findCityById(city_code).getAreas();
        return areas;
    }

    /**
     * @date: 2020/3/18
     * @description: 个人资料页面
     */

    @RequestMapping(value = "/personalCenter")
    public String personalCenter(Authentication authentication, HttpSession session){
        User user = loginService.findUserByPhone(authentication.getName());
        String role = user.getRole().getRole();
        int code = 1;
        int number = 0;
        int taskNumber = 0;
        int refuseTime = 0;
        int cancelTime = 0;
        int successTime = 0;
        int acceptTime = 0;
        int score = 0;
        if (role.equals("学生")){
            Student user1 = studentService.findStudentByUser(user);
            taskNumber = taskService.countTaskByStudent(user1);
            refuseTime = taskService.countTaskStudentAndResult(user1, "拒绝");
            cancelTime = taskService.countTaskStudentAndResult(user1, "取消");
            successTime = taskService.countTaskStudentAndResult(user1, "成功");
            acceptTime = taskService.countTaskStudentAndResult(user1, "接受");
            if (taskNumber != 0){
                score = ((-(refuseTime / taskNumber) + 1)  + (-(cancelTime / taskNumber) + 1) + (successTime / taskNumber) + (acceptTime / taskNumber)) * 5;
            }
            session.setAttribute("user", user1);
        }
        else {
            Parent user1 = parentService.findParentByPhone(authentication.getName());
            for (Detail detail : user1.getDetails()){
                taskNumber = taskNumber + taskService.countTaskByDetail(detail);
                refuseTime = taskService.countTaskDetailAndResult(detail, "拒绝");
                cancelTime = taskService.countTaskDetailAndResult(detail, "取消");
                successTime = taskService.countTaskDetailAndResult(detail, "成功");
                acceptTime = taskService.countTaskDetailAndResult(detail, "接受");
                if (taskNumber != 0){
                    score = ((-(refuseTime / taskNumber) + 1)  + (-(cancelTime / taskNumber) + 1) + (successTime / taskNumber) + (acceptTime / taskNumber)) * 5;
                }
            }
            session.setAttribute("user", user1);
        }
        session.setAttribute("taskNumber", taskNumber);
        session.setAttribute("refuseTime", refuseTime);
        session.setAttribute("cancelTime", cancelTime);
        session.setAttribute("successTime", successTime);
        session.setAttribute("acceptTime", acceptTime);
        session.setAttribute("score", score);
        session.setAttribute("code", code);
        session.setAttribute("number", number);
        return "student/student_home";
    }

    /**
     * @date: 2020/3/18
     * @description: 修改位置
     */

    @RequestMapping(value = "/changePosition")
    public String changePosition(Authentication authentication, @RequestParam(value = "province_name") String province_name, @RequestParam(value = "city_name") String city_name, @RequestParam(value = "area_name") String area_name, HttpSession session){
        User user = loginService.findUserByPhone(authentication.getName());
        String role = user.getRole().getRole();
        session.setAttribute("province", province_name);
        session.setAttribute("city", city_name);
        session.setAttribute("area", area_name);
        if (role.equals("学生")){
            return "student/student_home";
        }
        else if (role.equals("家长")){
            return "parent/parent_home";
        }
        return "";
    }

    /**
     * @date: 2020/2/23
     * @description: 查看消息
     */

    @RequestMapping(value = "/findInformation")
    public String findInformation(Authentication authentication, HttpSession session){
        int code = 3;
        User user = loginService.findUserByPhone(authentication.getName());
        String role = user.getRole().getRole();
        if (role.equals("学生")){
            Student student = studentService.findStudentByUser(user);
            List<Message> messages = messageService.findAllByStudentAndDisplayOrderByDate(student, true);
            int messageNumber = messageService.countByStudentAndState(student, false);
            session.setAttribute("messageNumber", messageNumber);
            session.setAttribute("messages", messages);
        }
        else if (role.equals("家长")){
            Parent parent = parentService.findParentByPhone(authentication.getName());
            List<Message> messages = messageService.findAllByParentAndDisplayOrderByDate(parent, true);
            int messageNumber = messageService.countByParentAndState(parent, false);
            session.setAttribute("messageNumber", messageNumber);
            session.setAttribute("messages", messages);
        }
        session.setAttribute("code", code);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/24
     * @description: 删除消息
     */

    @RequestMapping(value = "/deleteInformation")
    @ResponseBody
    public String deleteInformation(@RequestParam(value = "message_id") int id){
        Message message = messageService.findMessageById(id);
        message.setDisplay(false);
        messageService.saveMessage(message);
        return "success";
    }

    /**
     * @date: 2020/2/24
     * @description: 消息已读
     */

    @RequestMapping(value = "/readInformation")
    public String readInformation(@RequestParam(value = "message_id") int id){
        Message message = messageService.findMessageById(id);
        message.setState(true);
        messageService.saveMessage(message);
        return "redirect:/findInformation";
    }

    /**
     * @date: 2020/2/28
     * @description: 修改个人资料页面
     */

    @RequestMapping(value = "/modifyingData")
    public String modifyingData(Authentication authentication, HttpSession session){
        User user = loginService.findUserByPhone(authentication.getName());
        String role = user.getRole().getRole();
        int number = 8;
        if (role.equals("学生")){
            Student user1 = studentService.findStudentByUser(user);
            session.setAttribute("user", user1);
        }
        else {
            Parent user1 = parentService.findParentByPhone(authentication.getName());
            session.setAttribute("user", user1);
        }
        session.setAttribute("number", number);
        return "student/student_home";
    }

    /**
     * @date: 2020/2/29
     * @description: 修改个人资料
     */

    @RequestMapping(value = "/changeInformation")
    @ResponseBody
    public String changeInformation(@RequestParam(value = "information") String information, Authentication authentication, HttpSession session){
        Map<String,String> map = new HashMap<>();
        String string =information.replace("\"", "");
        String[] array = string.split(";");
        for(String str : array ){
            String[] newArray = str.split(",");
            System.out.println(str);
            map.put(newArray[0], newArray[1]);
        }
        User user = loginService.findUserByPhone(authentication.getName());
        String role = user.getRole().getRole();
        if (role.equals("学生")){
            Student student = studentService.findStudentByUser(user);
            student.setUsername(map.get("username"));
            student.setName(map.get("name"));
            student.setAge(map.get("age"));
            student.setGender(map.get("grade").charAt(0));
            student.setQualification(map.get("qualification"));
            student.setProvinceAndCity(map.get("province") + map.get("city") + map.get("area"));
            student.setGoodAtSubjects(map.get("goodAtSubjects"));
            student.setFreeTime(map.get("freeTime"));
            if (!"null".equals(map.get("detailedAddress"))){
                student.setAddress(map.get("detailedAddress"));
            }
            studentService.saveStudent(student);
        }
        else {
            Parent parent = parentService.findParentByPhone(authentication.getName());
            parent.setUsername(map.get("username"));
            parent.setName(map.get("name"));
            parent.setAge(map.get("age"));
            parent.setGender(map.get("gender").charAt(0));
            parent.setProvinceAndCity(map.get("province") + map.get("city") + map.get("area"));
            parent.setAchievements(map.get("achievements"));
            if (!"null".equals(map.get("detailedAddress"))){
                parent.setAddress(map.get("detailedAddress"));
            }
            parentService.saveParent(parent);
        }
        return "success";
    }

    /**
     * @date: 2020/3/14
     * @description: 修改资料中的选择空闲时间
     */

    @RequestMapping(value = "/chooseFreeTime")
    @ResponseBody
    public String chooseFreeTime(@RequestParam(value = "array") String array[][]){
        String[] week = {"周一","周二","周三","周四","周五","周六","周日"};
        Map<Integer, List<String>> map = new HashMap<>();
        for (int i = 0; i < 7; i++){
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
        String result = "";
        System.out.println(map);
        for (int i = 0; i < 7; i++){
            if (map.get(i) != null){
                result = result + week[i] + "：" + map.get(i).toString().replace("[", "").replace("]", "") + "<br>";
            }
        }
        return result;
    }

    /**
     * @date: 2020/3/14
     * @description: 修改资料中的选择年级和科目
     */

    @RequestMapping(value = "/chooseGradeAndSubject")
    @ResponseBody
    public String chooseGradeAndSubject(@RequestParam(value = "array") String array[][]){
        String[] grade = {"小学一年级", "小学二年级", "小学三年级", "小学四年级", "小学五年级", "小学六年级", "初一", "初二", "初三", "高一", "高二", "高三"};
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
        String result = "";
        System.out.println(map);
        for (int i = 0; i < array.length; i++){
            if (map.get(i) != null){
                result = result + grade[i] + "：" + map.get(i).toString().replace("[", "").replace("]", "") + "<br>";
            }
        }
        return result;
    }

    /**
     * @date: 2020/3/18
     * @description: 其它人的个人资料及介绍页面
     */

    @RequestMapping(value = "/viewIntroduction")
    public String viewIntroduction(HttpSession session){
        int code = 4;
        session.setAttribute("code", code);
        return "student/student_home";
    }


}
