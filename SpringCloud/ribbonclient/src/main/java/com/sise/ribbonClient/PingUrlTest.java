package com.sise.ribbonClient;

import com.netflix.client.ClientFactory;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.client.http.RestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: SpringCloud
 * @description: Lab3 Ping机制
 * @author: wxy
 * @create: 2020-01-16 10:54
 **/

public class PingUrlTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        ConfigurationManager.loadPropertiesFromResources("application.yml");
//        BaseLoadBalancer baseLoadBalancer = new BaseLoadBalancer();
//        List<Server> servers = new ArrayList<Server>();
//        servers.add(new Server("localhost", 8081));
//        servers.add(new Server("localhost", 8888));     //非法接口8888
//        baseLoadBalancer.addServers(servers);
//        baseLoadBalancer.setPing(new PingUrl());        //设置IPing的实现类PingUrl
//        baseLoadBalancer.setPingInterval(2);            //时间间隔
        RestClient client = (RestClient) ClientFactory.getNamedClient("my-client");
        Thread.sleep(6000);
        for (Server server : client.getLoadBalancer().getAllServers()){
            System.out.println(server.getHostPort() + "的状态：" + server.isAlive());
        }
    }

}
