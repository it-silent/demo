package com.hy.demo.sys.interceptor;

import com.hy.demo.common.utils.JwtUtils;
import com.hy.demo.controller.base.BaseController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ControllerInterceptor
 *                  controller方法执行之前的判断验证等操作(例如登陆判断)
 *
 * @author yuhaiyang
 * @date   2018/5/29
 */
public class ControllerInterceptor implements HandlerInterceptor {

    /**
     * handler 调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod method = (HandlerMethod)handler;
            if (method.getBean() instanceof BaseController) {
                String token = request.getHeader("Token");
                boolean verify = JwtUtils.verify(token);
                System.err.printf("Token:%s",verify);
            }
        }
        return false;
    }

    /**
     *  handler 调用之后，and 渲染之前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     *  handler 调用之后，and 渲染之后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
