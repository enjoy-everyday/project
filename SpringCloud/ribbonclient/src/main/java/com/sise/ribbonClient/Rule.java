package com.sise.ribbonClient;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

/**
 * @program: SpringCloud
 * @description: Lab3 自定义负载规则
 * @author: wxy
 * @create: 2020-01-15 21:19
 **/

public class Rule implements IRule {

    ILoadBalancer iLoadBalancer;
    public Rule(){}

    public Rule(ILoadBalancer iLoadBalancer){
        this.iLoadBalancer = iLoadBalancer;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.iLoadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.iLoadBalancer;
    }

    /**
     * @date: 2020/1/16
     * @description: Lab3-2
     */

//    @Override
//    public Server choose(Object o) {
//        List<Server> servers = iLoadBalancer.getAllServers();
//        return servers.get(0);
//    }

    /**
     * @date: 2020/1/16
     * @description: Lab3-4
     */

    @Override
    public Server choose(Object o) {
        List<Server> servers = iLoadBalancer.getAllServers();
        Random random = new Random();
        final int number = random.nextInt(10);
        if (number < 7){
            return findServer(servers, 8081);
        }
        return findServer(servers, 8085);
    }

    public Server findServer(List<Server> servers, int port){
        for (Server server : servers){
            if (server.getPort() == port){
                return server;
            }
        }
        System.out.println("端口号为：" + port);
        return null;
    }

}
