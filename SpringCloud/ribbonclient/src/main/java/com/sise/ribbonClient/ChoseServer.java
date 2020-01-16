package com.sise.ribbonClient;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.cloud.client.ServiceInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: SpringCloud
 * @description: Lab3 添加服务器
 * @author: wxy
 * @create: 2020-01-15 21:11
 **/

public class ChoseServer {

    public static void main(String[] args){
        //ILoadBalancer iLoadBalancer = new BaseLoadBalancer();     //创建负载均衡器
        BaseLoadBalancer baseLoadBalancer = new BaseLoadBalancer();
        baseLoadBalancer.setRule(new Rule(baseLoadBalancer));
        //添加服务器
        List<Server> servers = new ArrayList<Server>();
        servers.add(new Server("localhost", 8081));
        servers.add(new Server("localhost", 8085));
//        iLoadBalancer.addServers((servers));
        baseLoadBalancer.addServers((servers));
        //服务器选择
        for (int i = 0; i < 6; i++){
//            Server server = iLoadBalancer.chooseServer(null);
            Server server = baseLoadBalancer.chooseServer(null);
            System.out.println(server);
        }

    }

}
