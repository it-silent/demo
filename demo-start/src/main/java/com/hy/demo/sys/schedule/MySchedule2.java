package com.hy.demo.sys.schedule;

import com.hy.demo.common.utils.TimeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

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
public class MySchedule2 {

    //    @Scheduled(cron = "0/1 * * * * *")
//    @Scheduled(fixedRate = 2000)
    public void sysTime2() {
        try {
            System.out.println(String.format("[2000-%s] schedule2.class start", TimeUtils.format(new Date())));

//            int a = 1 / 0;
            System.out.println("[2000]time now is : " + TimeUtils.format(new Date()));
        } catch (Exception e) {
            System.out.println(String.format("[2000-%s]schedual2.err: %s", TimeUtils.format(new Date()), e.getMessage()));
        }
        System.out.println("--------------------------------------------------");
    }
}
