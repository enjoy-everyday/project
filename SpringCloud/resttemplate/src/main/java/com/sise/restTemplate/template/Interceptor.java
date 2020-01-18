package com.sise.restTemplate.template;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @program: SpringCloud
 * @description: RestTemplate
 * @author: wxy
 * @create: 2020-01-16 18:51
 **/

public class Interceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        System.out.println("********自定义拦截器********");
        System.out.println("原来URI：" + httpRequest.getURI());
        MyHttpRequest newRequest = new MyHttpRequest(httpRequest);
        System.out.println("新URI：" + newRequest.getURI());
        return clientHttpRequestExecution.execute(newRequest, bytes);
    }
}
