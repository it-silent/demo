package com.hy.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hy.demo.common.CommonResult;
import com.hy.demo.controller.base.BaseController;
import com.hy.demo.dal.dataobject.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;

/**
 * TestController
 *
 * @author yuhaiyang
 * @date 2018/6/1
 */
@RestController
@RequestMapping(path = "/test")
public class TestController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping(path = "/fastNull")
    public CommonResult fastJsonMapNull() {
        UserDO userDO = new UserDO();
        userDO.setName("fastJson");
        userDO.setDeptId(null);
        userDO.setDeptName("lawasha");
        userDO.setEmail("@@@");
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(userDO));
        return CommonResult.success(jsonObject);
    }

    @GetMapping(path = "/jacksonNull")
    public CommonResult jacksonMapNull() throws Exception{
        UserDO userDO = new UserDO();
        userDO.setName("fastJson");
        userDO.setDeptId(null);
        userDO.setDeptName("lawasha");
        userDO.setEmail("@@@");
        String s = new ObjectMapper().writeValueAsString(userDO);
        ObjectNode data = new ObjectMapper().readValue(s, ObjectNode.class);
        data.put("testNullValue", (String) null);
        data.put("testNullValue2", (String) null);
        return CommonResult.success(data);
    }
}
