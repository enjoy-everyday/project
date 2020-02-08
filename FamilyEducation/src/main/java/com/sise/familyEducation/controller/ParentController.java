package com.sise.familyEducation.controller;

import com.sise.familyEducation.entity.*;
import com.sise.familyEducation.repository.DetailRepository;
import com.sise.familyEducation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

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
    private ParentService parentService;
    @Autowired
    private DetailRepository detailRepository;
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
        detailRepository.save(detail);
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
        task.setWhetherToPass("是");
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
        if (task.getHistoricalDetail() == null){
            HistoricalDetail historicalDetail = new HistoricalDetail();
            historicalDetail.setDate(new Date().toString());
            historicalDetail.setContext(task.getDetail().getContext());
            historicalDetail.setParent(task.getDetail().getParent());
            historicalDetailService.saveHistoricalDetail(historicalDetail);
            for (Task task1 : task.getDetail().getTasks()){
                task1.setHistoricalDetail(historicalDetail);
            }
        }
        HistoricalTask historicalTask = new HistoricalTask();
        historicalTask.setDate(new Date().toString());
        historicalTask.setResult("拒绝");
        historicalTask.setHistoricalDetail(task.getHistoricalDetail());
        historicalTask.setStudent(task.getStudent());
        historicalTaskService.saveHistoricalTask(historicalTask);
        taskService.deleteTaskById(id);
        return "redirect:/viewCandidates";
    }

}
