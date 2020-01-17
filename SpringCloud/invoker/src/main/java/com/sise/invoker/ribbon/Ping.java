package com.sise.invoker.ribbon;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

/**
 * @program: SpringCloud
 * @description: Lab4
 * @author: wxy
 * @create: 2020-01-16 14:30
 **/

public class Ping implements IPing {

    @Override
    public boolean isAlive(Server server) {
        System.out.println("自定义Ping：" + server.getHostPort());
        return true;
    }

}
