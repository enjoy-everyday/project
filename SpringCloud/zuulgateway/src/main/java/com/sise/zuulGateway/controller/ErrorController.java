package com.sise.zuulGateway.controller;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: SpringCloud
 * @description: Lab11
 * @author: wxy
 * @create: 2020-01-29 14:47
 **/

@Controller
public class ErrorController extends BasicErrorController {

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes, new ErrorProperties());
    }

    public ModelAndView errorHtml(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        System.out.println("******输出异常信息******");
        System.out.println(httpServletRequest.getAttribute("javax.servlet.error.status_code"));
        System.out.println(httpServletRequest.getAttribute("javax.servlet.error.exception"));
        System.out.println(httpServletRequest.getAttribute("javax.servlet.error.message"));
        return super.errorHtml(httpServletRequest, httpServletResponse);
    }

}
