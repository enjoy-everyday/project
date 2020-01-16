package com.sise.ribbonClient;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

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
    public Server choose(Object o) {
        List<Server> servers = iLoadBalancer.getAllServers();
        return servers.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.iLoadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.iLoadBalancer;
    }
}
