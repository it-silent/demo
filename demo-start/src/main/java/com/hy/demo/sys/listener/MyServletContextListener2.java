package com.hy.demo.sys.listener;

import com.hy.demo.service.stt.SttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * MyServletContextListener
 *                  监听器
 * @author silent
 * @date 2018/4/23
 */
@WebListener
public class MyServletContextListener2 implements ServletContextListener {

    @Autowired
    private SttService sttService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            System.err.println("servlet starting ....");
            System.err.println(sttService.findAllByPageAndSort(0, 1, Sort.by(Sort.Order.desc("gmtModified"))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
