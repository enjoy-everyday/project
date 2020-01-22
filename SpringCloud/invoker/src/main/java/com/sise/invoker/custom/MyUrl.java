package com.sise.invoker.custom;

import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 * @program: SpringCloud
 * @description: Lab6 实验2
 * @author: wxy
 * @create: 2020-01-20 22:44
 **/

@Target(METHOD)
@Retention(RUNTIME)
public @interface MyUrl {

    String url();
    String method();

}
