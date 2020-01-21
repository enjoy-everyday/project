package com.sise.feignClient.custom;

import feign.Client;
import feign.Request;
import feign.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;

/**
 * @program: SpringCloud
 * @description: Lab6 自定义Feign客户端
 * @author: wxy
 * @create: 2020-01-20 15:05
 **/

public class CustomFeignClient implements Client {

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        System.out.println("********自定义Feign客户端********");
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();      //创建默认客户端
        String method = request.method();       //获取调用的HTTP方法
        HttpRequestBase httpRequestBase = new HttpRequestBase() {       //创建一个HttpRequest
            @Override
            public String getMethod() {
                return method;
            }
        };
        try {
            httpRequestBase.setURI(new URI(request.url()));     //设置请求地址
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpResponse httpResponse = closeableHttpClient.execute(httpRequestBase);       //执行请求获取响应
        byte[] bytes = EntityUtils.toByteArray(httpResponse.getEntity());       //获取响应的主体内容
        //将HttpClient的响应对象转换为Feign的Response
        Response response = Response.builder().body(bytes).headers(new HashMap<String, Collection<String>>()).status(httpResponse.getStatusLine().getStatusCode()).build();
        return response;
    }
}
