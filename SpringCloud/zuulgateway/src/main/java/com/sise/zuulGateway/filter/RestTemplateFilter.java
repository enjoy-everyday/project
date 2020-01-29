package com.sise.zuulGateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: SpringCloud
 * @description: Lab11
 * @author: wxy
 * @create: 2020-01-28 16:16
 **/

public class RestTemplateFilter extends ZuulFilter {

    private RestTemplate restTemplate;

    public RestTemplateFilter(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        //获取请求的uri
        String uri = httpServletRequest.getRequestURI();
        //为了不影响其它路由，uri中含有sale，才执行本路由
//        if (uri.indexOf("sale") != -1){
//            return true;
//        }
//        else {
//            return false;
//        }
        return uri.contains("sale");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        String serviceId = (String) requestContext.get("serviceId");        //获取调用的服务id
        String uri = (String) requestContext.get("requestURI");     //获取请求的uri
        String url = "http://" + serviceId + uri;       //组合成url给RestTemplate调用
        System.out.println("URL:" + url);
        System.out.println(serviceId);
        System.out.println(uri);
        String result = this.restTemplate.getForObject(url, String.class);      //调用并获取结果
        requestContext.setResponseBody(result);     //设置路由状态，表示已经进行路由
        requestContext.sendZuulResponse();      //设置响应标识
        return null;
    }

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }
}
