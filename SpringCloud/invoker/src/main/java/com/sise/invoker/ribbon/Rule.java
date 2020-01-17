package com.sise.invoker.ribbon;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

/**
 * @program: SpringCloud
 * @description: Lab4
 * @author: wxy
 * @create: 2020-01-16 14:27
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
     * @description: Lab4
     */

    @Override
    public Server choose(Object o) {
        List<Server> servers = iLoadBalancer.getAllServers();
        System.out.println("自定义服务器规则类，服务器信息：");
        for (Server server : servers){
            System.out.println(server.getHostPort());
        }
        return servers.get(0);
    }

}
