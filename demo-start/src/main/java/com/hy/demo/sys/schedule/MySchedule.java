package com.hy.demo.sys.schedule;

import com.hy.demo.common.utils.ExecutorUtils;
import com.hy.demo.common.utils.TimeUtils;
import com.sun.tools.corba.se.idl.constExpr.Times;
import org.apache.commons.lang3.ThreadUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MySchedule
 * springboot 注解定时任务
 *
 * @author silent
 * @date 2018/4/23
 */
//@Configuration
//@EnableScheduling
public class MySchedule {

    //    @Scheduled(cron = "0/1 * * * * *")
    @Scheduled(fixedRate = 1000)
    public void sysTime() {
        ExecutorUtils.execute(() -> {
            System.err.println(String.format("[1000-%s] schedule1 start", TimeUtils.format(new Date())));

//        int a = 1 / 0;
            System.err.println("[1000]time now is : %s" + TimeUtils.format(new Date()));
            System.err.println("--------------------------------------------------");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Scheduled(fixedRate = 2000)
    public void sysTime2() {
        try {
            System.err.println(String.format("[2000-%s] schedule2 start", TimeUtils.format(new Date())));

//            int a = 1 / 0;
            System.err.println("[2000]time now is : " + TimeUtils.format(new Date()));
        } catch (Exception e) {
            System.err.println(String.format("[2000-%s]schedual2.err: %s", TimeUtils.format(new Date()), e.getMessage()));
        }
        System.err.println("--------------------------------------------------");
    }
}
