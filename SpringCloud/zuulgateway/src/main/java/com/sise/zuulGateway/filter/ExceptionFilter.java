//package com.sise.zuulGateway.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import org.bouncycastle.cert.ocsp.Req;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @program: SpringCloud
// * @description: Lab11
// * @author: wxy
// * @create: 2020-01-29 14:39
// **/
//
//public class ExceptionFilter extends ZuulFilter {
//    @Override
//    public String filterType() {
//        return FilterConstants.ROUTE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return 3;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest httpServletRequest = requestContext.getRequest();
//        String uri = httpServletRequest.getRequestURI();
//        if (uri.indexOf("exception") != -1){
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        System.out.println("抛出异常");
//        throw new ZuulRuntimeException(new ZuulException("exception msg", 201, "my cause"));
//    }
//}
