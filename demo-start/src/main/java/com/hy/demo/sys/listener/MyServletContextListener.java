package com.hy.demo.sys.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * MyServletContextListener
 *                  监听器--定时任务
 * @author silent
 * @date 2018/4/23
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.err.println("定时task ing--"+ TimeUtils.format(new Date()));
//            }
//        }, Duration.ofSeconds(5).toMillis(), Duration.ofSeconds(3).toMillis());


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
