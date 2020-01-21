package com.sise.feignClient.custom;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @program: SpringCloud
 * @description: Lab6 自定义解码器
 * @author: wxy
 * @create: 2020-01-20 14:57
 **/

public class CustomDecoder implements Decoder {

    private GsonDecoder gsonDecoder;

    public CustomDecoder(){
        gsonDecoder = new GsonDecoder();
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        System.out.println("自定义解码器");
        return gsonDecoder.decode(response, type);
    }
}
