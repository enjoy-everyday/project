package com.sise.invoker.custom;

import feign.MethodMetadata;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @program: SpringCloud
 * @description: Lab6 实验2
 * @author: wxy
 * @create: 2020-01-20 22:52
 **/

public class MyContract extends SpringMvcContract {

    @SuppressWarnings("deprecation")
    protected void processAnnotationOnMethod(MethodMetadata methodMetadata, Annotation annotation, Method method) {
        super.processAnnotationOnMethod(methodMetadata, annotation, method);
        if (MyUrl.class.isInstance(annotation)){         //判断是否是自定义的那个注解
            MyUrl myUrl = method.getAnnotation(MyUrl.class);       //获取注解实例
            String httpMethod = myUrl.method();      //获取注解配置的Http方法
            String url = myUrl.url();        //获取注解配置的Url
            //将url、method属性分别设置到Feign的模板中
            methodMetadata.template().method(httpMethod);
            methodMetadata.template().append(url);
        }
    }

}
