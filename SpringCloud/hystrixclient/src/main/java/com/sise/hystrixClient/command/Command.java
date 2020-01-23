package com.sise.hystrixClient.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @program: SpringCloud
 * @description: Lab7
 * @author: wxy
 * @create: 2020-01-21 16:46
 **/

public class Command extends HystrixCommand<String> {

    /**
     * @date: 2020/1/21
     * @description: Lab7
     */

    private String url;
    CloseableHttpClient closeableHttpClient;

    public Command(String url){
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.closeableHttpClient = HttpClients.createDefault();
        this.url = url;
    }

    @Override
    protected String run() throws Exception {
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
        return EntityUtils.toString(httpResponse.getEntity());
    }

    protected String getFallback(){
        System.out.println("执行回退方法");
        return "error";
    }

}
