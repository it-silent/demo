package com.hy.demo.controller;

import com.hy.demo.common.CommonResult;
import com.hy.demo.controller.base.BaseController;
import com.hy.demo.dal.dataobject.SttDO;
import com.hy.demo.service.stt.SttService;
import com.hy.demo.utils.WebUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;

/**
 * SttController
 *
 * @author yuhaiyang
 * @date 2018/5/30
 */
@RestController
@RequestMapping(path = "/stt")
public class SttController extends BaseController {

    @Autowired
    private SttService sttService;

    @GetMapping(path = "/list")
    public CommonResult list() throws Exception {
        String cookieValue = null;
        if (ArrayUtils.isNotEmpty(request.getCookies())) {
            for (Cookie cookie : request.getCookies()) {
                if ("cookie-test-yhy".equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                    cookie.setPath("/");
                    cookie.setDomain(WebUtils.getWebServer(request));
                    break;
                }
            }
        }
        return CommonResult.success(sttService.findAll(), cookieValue);
    }

    @GetMapping(path = "/info/{id}")
    public CommonResult info(@PathVariable Long id) throws Exception {
        return CommonResult.success(sttService.findById(id));
    }

    @PostMapping(path = "/save")
    public CommonResult save(@RequestBody SttDO sttDO) throws Exception {
        sttService.create(sttDO.getCreator(), sttDO.getName(), sttDO.getLeaderId(), sttDO.getMasterId());
        return CommonResult.success();
    }

    @PutMapping(path = "/update")
    public CommonResult update(@RequestBody SttDO stt) throws Exception {
        sttService.update(stt.getId(), stt.getName(), stt.getMasterId(), stt.getLeaderId());
        return CommonResult.success();
    }

    @PutMapping(path = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id) throws Exception {
        sttService.delete(id);
        return CommonResult.success();
    }
}
