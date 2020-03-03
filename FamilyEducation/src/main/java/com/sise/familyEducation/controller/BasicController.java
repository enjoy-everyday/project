package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.*;
import com.sise.familyEducation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: FamilyEducation
 * @description: 基础
 * @author: wxy
 * @create: 2020-02-08 21:17
 **/

@Controller
public class BasicController {

    @Autowired
    private BasicService basicService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/getProvince")
    @ResponseBody
    public List<Province> getAllProvinces(){
        List<Province> provinces = basicService.findAllProvince();
        return provinces;
    }

    @RequestMapping(value = "/getCities")
    @ResponseBody
    public List<City> getCities(@RequestParam(value = "province_code") int province_code){
        List<City> cities = basicService.findProvinceById(province_code).getCities();
        return cities;
    }

    @RequestMapping(value = "/getAreas")
    @ResponseBody
    public List<Area> getAreas(@RequestParam(value = "city_code") int city_code){
        List<Area> areas = basicService.findCityById(city_code).getAreas();
        return areas;
    }

    @RequestMapping(value = "/personalCenter")
    public String personalCenter(HttpSession session){
        int code = 1;
        int number = 0;
        session.setAttribute("code", code);
        session.setAttribute("number", number);
        return "student/student_home";
    }

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
    public String deleteInformation(@RequestParam(value = "message_id") int id){
        Message message = messageService.findMessageById(id);
        message.setDisplay(false);
        messageService.saveMessage(message);
        return "redirect:/findInformation";
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
     * @description: 个人资料页面
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
    public String changeInformation(@RequestParam(value = "information") String information, Authentication authentication){
        Map<String,String> map = new HashMap<>();
        String string =information.replace("\"", "");
        String[] array = string.split(",");
        for(String str : array ){
            String[] newArray = str.split(":");
            System.out.println(str);
            map.put(newArray[0], newArray[1]);
        }
        User user = loginService.findUserByPhone(authentication.getName());
        String role = user.getRole().getRole();
        if (role.equals("学生")){
            Student student = new Student();
            student.setUsername(map.get("username"));
            student.setQualification(map.get("qualification"));
            studentService.saveStudent(student);
        }
        else {
            Parent parent = new Parent();
            parent.setUsername(map.get("username"));
            parent.setName(map.get("name"));
            parentService.saveParent(parent);
        }
        return "";
    }


}
