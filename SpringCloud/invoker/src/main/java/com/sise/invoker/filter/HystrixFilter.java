package com.sise.invoker.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @program: SpringCloud
 * @description: Lab9
 * @author: wxy
 * @create: 2020-01-24 15:26
 **/

@WebFilter(urlPatterns = "/*", filterName = "hystrixFilter")
public class HystrixFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            chain.doFilter(request, response);
        }
        finally {
            context.shutdown();
        }
    }

    @Override
    public void destroy() {}

}
