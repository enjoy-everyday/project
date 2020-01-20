package com.sise.restServer.controller;

import com.sise.restServer.entity.Person;
import com.sise.restServer.entity.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * @program: SpringCloud
 * @description: Lab5 CXF
 * @author: wxy
 * @create: 2020-01-17 14:44
 **/

@RestController
public class Controller {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findPerson(@PathVariable("id") Integer id, HttpServletRequest request){
        Person person = new Person();
        person.setId(id);
        person.setName("TOMMY");
        person.setAge(30);
        person.setMessage(request.getRequestURL().toString());
        return person;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        return "Hello";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) //consume是指定请求内容类型
    @ResponseBody
    public String createPerson(@RequestBody Person person){
        System.out.println(person.getName() + "-" + person.getAge());
        return "ID:" + person.getId();
    }

    //produces是指定返回内容类型
    @RequestMapping(value = "/createXML", method = RequestMethod.POST,consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String createXMLPerson(@RequestBody Person person){
        System.out.println(person.getName() + "-" + person.getId());
        return "<result><message>Success</message></result>";
    }


    /**
     * @date: 2020/1/18
     * @description: 以下是实验内容 student
     */

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Student findStudentById(@PathVariable("id") Integer id){
        Student student= new Student();
        if (id == 901){
            student.setId(id);
            student.setName("TOMMY");
            student.setChinese(89);
            student.setMath(80);
            student.setEnglish(75);
            return student;
        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "/student/sum", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> Sum(){
        Student student= new Student();
        student.setId(901);
        student.setName("TOMMY");
        student.setChinese(89);
        student.setMath(80);
        student.setEnglish(75);
        Student student1= new Student();
        student1.setId(902);
        student1.setName("TOMMY");
        student1.setChinese(20);
        student1.setMath(89);
        student1.setEnglish(75);
        List<Student> students = new ArrayList<Student>();
        students.add(student);
        students.add(student1);
        return students;
    }

    @RequestMapping(value = "/student/createStudent", method = RequestMethod.POST,consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String createXMLStudent(@RequestBody Student student){
        int average = (student.getChinese() + student.getMath() + student.getEnglish()) / 3;
        return "<student><average>" + average + "</average></student>";
    }

}
