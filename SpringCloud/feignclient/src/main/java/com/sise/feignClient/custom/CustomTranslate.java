package com.sise.feignClient.custom;

import feign.Contract;
import feign.MethodMetadata;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @program: SpringCloud
 * @description: Lab6 自定义翻译器
 * @author: wxy
 * @create: 2020-01-20 15:42
 **/

public class CustomTranslate extends Contract.BaseContract {

    //类处理的注解
    @Override
    protected void processAnnotationOnClass(MethodMetadata methodMetadata, Class<?> aClass) {}

    //方法级处理的注解
    @Override
    protected void processAnnotationOnMethod(MethodMetadata methodMetadata, Annotation annotation, Method method) {
        if (CustomAnnotation.class.isInstance(annotation)){         //判断是否是自定义的那个注解
            CustomAnnotation customAnnotation = method.getAnnotation(CustomAnnotation.class);       //获取注解实例
            String httpMethod = customAnnotation.method();      //获取注解配置的Http方法
            String url = customAnnotation.url();        //获取注解配置的Url
            //将url、method属性分别设置到Feign的模板中
            methodMetadata.template().method(httpMethod);
            methodMetadata.template().append(url);
        }
    }

    //参数处理的注解
    @Override
    protected boolean processAnnotationsOnParameter(MethodMetadata methodMetadata, Annotation[] annotations, int i) {
        return false;
    }

}
