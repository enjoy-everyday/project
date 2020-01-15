package com.sise.provider.handler;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.sise.provider.healthIndicator.MyHealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * @program: SpringCloud
 * @description: Lab2-2
 * @author: wxy
 * @create: 2020-01-14 15:31
 **/

@Component
public class MyHealthCheckHandler implements HealthCheckHandler {

    @Autowired
    private MyHealthIndicator indicator;
    @Override
    public InstanceStatus getStatus(InstanceStatus instanceStatus) {
        Status status = indicator.health().getStatus();
        if (status.equals(Status.UP)){
            System.out.println("数据库正常连接");
            return InstanceStatus.UP;
        }
        else {
            System.out.println("数据库无法连接");
            return InstanceStatus.DOWN;
        }
    }
}
