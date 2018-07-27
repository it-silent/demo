package com.hy.demo.sys.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * DruidServlet
 *
 * @author silent
 * @date 2018/5/20
 */
@WebServlet(urlPatterns = {"/druid/*"}, initParams = {
        //IP白名单
        @WebInitParam(name = "allow", value = "127.0.0.1"),
        //IP黑名单
        @WebInitParam(name = "deny", value = "192.168.199.2"),
        //用户名
        @WebInitParam(name = "loginUsername", value = "silent"),
        //密码
        @WebInitParam(name = "loginPassword", value = "123456"),
        //禁用html页面上的'reset all'功能
        @WebInitParam(name = "resetEnable", value = "false")
})
public class DruidStatViewServlet extends StatViewServlet {

}
