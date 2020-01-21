package com.sise.feignClient.custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: SpringCloud
 * @description: Lab6 自定义注解
 * @author: wxy
 * @create: 2020-01-20 15:37
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotation {

    //定义注解的url和method属性
    String url();
    String method();

}
