package com.hy.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoggerTest
 *
 * @author silent
 * @date 2018/5/16
 */
public class LoggerTest {

    private static Logger logger = LoggerFactory.getLogger(LoggerTest.class);


    public static void main(String[] args) {
        logger.debug("debug");
        logger.info("logger 1");
        logger.warn("warn");
        logger.error("err:");
    }
}
