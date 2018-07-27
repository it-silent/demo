package com.hy.demo.controller;

import com.hy.demo.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;

/**
 * HtmlController
 *
 * @author silent
 * @date 2018/4/21
 */
@Controller
public class HtmlController extends BaseController {

    @RequestMapping(path = "/html")
    public String index() {
        return "index";
    }

    @GetMapping("/resp/cookie/{cookie}")
    public String img(@PathVariable String cookie) {
        Cookie cookie1 = new Cookie("cookie-test-yhy", String.format("cookie-value-%s-end", cookie));
        cookie1.setPath("/");
        cookie1.setDomain("hy.com");
        cookie1.setMaxAge(Integer.MAX_VALUE);//单位:s；
        response.addCookie(cookie1);
        return "redirect:/stt/list";
    }

    @GetMapping(path = "/ajax")
    public String ajax() {
        return "ajax";
    }
}
