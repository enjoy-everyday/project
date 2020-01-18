package com.sise.restTemplate.template;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @program: SpringCloud
 * @description: RestTemplate
 * @author: wxy
 * @create: 2020-01-16 18:44
 **/

public class MyHttpRequest implements HttpRequest {

    private  HttpRequest httpRequest;
    public MyHttpRequest(HttpRequest httpRequest){
        this.httpRequest = httpRequest;
    }

    @Override
    public HttpMethod getMethod() {
        return httpRequest.getMethod();
    }

    @Override
    public String getMethodValue() {
        return null;
    }

    @Override
    public URI getURI() {
        String oldUri = httpRequest.getURI().toString();
        try {
            URI newUri = new URI("http://localhost:8080/hello");
            return newUri;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return httpRequest.getURI();
    }

    @Override
    public HttpHeaders getHeaders() {
        return httpRequest.getHeaders();
    }
}
