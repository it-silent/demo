package com.hy.demo.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * WebUtils
 *
 * @author yuhaiyang
 * @date 2018/5/30
 */
public class WebUtils {

    public static String getURL(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    public static String getWebServer(HttpServletRequest request) {
        return request.getServerName();
    }

    public static String getWebScheme(HttpServletRequest request) {
        return request.getScheme();
    }

    public static String getURI(HttpServletRequest request) {
        return request.getRequestURI();
    }

    public static String getQueryString(HttpServletRequest request) {
        return request.getQueryString();
    }

}
