//package com.sise.zuulGateway.custom;
//
//import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.ClientHttpResponse;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * @program: SpringCloud
// * @description: Lab11
// * @author: wxy
// * @create: 2020-01-28 15:38
// **/
//
//public class CustomFallbackProvider implements FallbackProvider {
//
//    //返回需要为哪个微服务提供回退
//    @Override
//    public String getRoute() {
//        return "book-server";
//    }
//
//    //回退触发时，返回默认的响应
//    @Override
//    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
//        return new ClientHttpResponse() {
//            @Override
//            public HttpStatus getStatusCode() throws IOException {
//                return HttpStatus.OK;
//            }
//
//            @Override
//            public int getRawStatusCode() throws IOException {
//                return 200;
//            }
//
//            @Override
//            public String getStatusText() throws IOException {
//                return "ok";
//            }
//
//            @Override
//            public void close() {}
//
//            @Override
//            public InputStream getBody() throws IOException {
//                return new ByteArrayInputStream("CustomFallback".getBytes());
//            }
//
//            @Override
//            public HttpHeaders getHeaders() {
//                HttpHeaders httpHeaders = new HttpHeaders();
//                httpHeaders.setContentType(MediaType.TEXT_PLAIN);
//                return httpHeaders;
//            }
//        };
//    }
//
//}
