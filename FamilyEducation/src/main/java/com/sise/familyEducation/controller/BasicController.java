package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.Area;
import com.sise.familyEducation.entity.City;
import com.sise.familyEducation.entity.Province;
import com.sise.familyEducation.entity.User;
import com.sise.familyEducation.service.BasicService;
import com.sise.familyEducation.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping(value = "/getProvince")
    @ResponseBody
    public List<Province> getAllProvinces(){
        List<Province> provinces = basicService.findAllProvince();
        System.out.println(provinces.get(0));
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

    @RequestMapping(value = "/changePosition")
    public String changePosition(Authentication authentication, @RequestParam(value = "province_name") String province_name, @RequestParam(value = "city_name") String city_name, @RequestParam(value = "area_name") String area_name, HttpSession session){
        User user = loginService.findUserByPhone(authentication.getName());
        String role = user.getRole().getRole();
        session.setAttribute("province", province_name);
        session.setAttribute("city", city_name);
        session.setAttribute("area", area_name);
        if (role.equals("学生")){
            System.out.println(user.getRole().getRole());
            return "student/student_home";
        }
        else if (role.equals("家长")){
            System.out.println(user.getRole().getRole());
            return "parent/parent_home";
        }
        return "";
    }

}
