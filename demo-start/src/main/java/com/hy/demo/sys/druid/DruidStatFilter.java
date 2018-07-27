package com.hy.demo.sys.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * DruidFilter
 *
 * @author silent
 * @date 2018/5/20
 */
@WebFilter(filterName = "druidFilter", urlPatterns = {"/*"}, initParams = {
        //忽略资源
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
})
public class DruidStatFilter extends WebStatFilter {
}
