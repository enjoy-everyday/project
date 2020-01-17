package com.sise.ribbonClient;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

/**
 * @program: SpringCloud
 * @description: Lab3 自定义Ping
 * @author: wxy
 * @create: 2020-01-16 13:47
 **/

public class Ping implements IPing {

    @Override
    public boolean isAlive(Server server) {
        System.out.println("自定义Ping：" + server.getHostPort());
        return true;
    }

}
