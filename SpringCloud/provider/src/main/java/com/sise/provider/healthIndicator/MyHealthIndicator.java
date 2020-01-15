package com.sise.provider.healthIndicator;

import com.sise.provider.controller.HealthController;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud
 * @description: Lab2-2
 * @author: wxy
 * @create: 2020-01-14 12:03
 **/

@Component
public class MyHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        if (HealthController.canVisitDb){
            return new Health.Builder(Status.UP).build();
        }
        else {
            return new Health.Builder(Status.DOWN).build();
        }
    }
}
