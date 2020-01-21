package com.sise.feignClient.custom;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.gson.GsonEncoder;

import java.lang.reflect.Type;

/**
 * @program: SpringCloud
 * @description: Lab6 自定义编码器
 * @author: wxy
 * @create: 2020-01-20 14:47
 **/

public class CustomEncoder implements Encoder {

    private GsonEncoder gsonEncoder;

    public CustomEncoder(){
        gsonEncoder = new GsonEncoder();
    }

    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {
        System.out.println("自定义编码器");
        System.out.println("ObjectClass：" + o.getClass().getName());
        System.out.println("ObjectValue：" + o);
        System.out.println("BodyTypeClass：" + type.getClass().getName());
        System.out.println("BodyTypeValue：" + type);
        gsonEncoder.encode(o, type, requestTemplate);
    }
}
