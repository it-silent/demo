package com.hy.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hy.demo.common.CommonResult;
import com.hy.demo.common.utils.TimeUtils;
import com.hy.demo.controller.base.BaseController;
import com.hy.demo.dal.dataobject.SttDO;
import com.hy.demo.service.stt.SttService;
import com.hy.demo.service.test.DDService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * MainController
 *
 * @author wb-yhy282733
 */
@RestController
public class MainController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @Value("${connection.maxActive}")
    private int maxActive;

    @Autowired
    private DDService ddService;

    @Autowired
    private SttService sttService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/")
    public String root() {
        return "demo test start";
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public CommonResult test() {
        return CommonResult.success("test"+maxActive);
    }

    @GetMapping("/json")
    public CommonResult json() {
//        JSONObject result = new JSONObject();
//        result.put("name", "silent");
//        result.put("sex", "man");
//        result.put("age", "18");
        return CommonResult.success();
    }

    @GetMapping(path = "DDService/test/{name}")
    public CommonResult derviceTest(@PathVariable String name) {
        return CommonResult.success(ddService.serviceTest(name));
    }

    @GetMapping(path = "/stt/{id}")
    public CommonResult find(@PathVariable Long id) {
        return CommonResult.success(sttService.findById(id));
    }

    @GetMapping(path = "/logging/test")
    public CommonResult loggingTest() {
        logger.debug("debug:" + TimeUtils.format(new Date()));
        logger.info("info:" + TimeUtils.format(new Date()));
        logger.warn("warn:" + TimeUtils.format(new Date()));
        logger.error("error:" + TimeUtils.format(new Date()));

        return CommonResult.success();
    }

    @GetMapping(path = "/err/{value}")
    public CommonResult err(@PathVariable int value) {
        int i = 5 / value ;
        return CommonResult.success(i);
    }

    @GetMapping(path = "/test/time")
    public CommonResult testTime() {
        SttDO sttDO = sttService.findById(3L);
        sttDO.setTestTime(LocalDateTime.now());
        sttDO.setTestTime2(new Date());
        return CommonResult.success(sttDO);
    }

    @ApiOperation(value = "jackson", notes = "jackson")
    @GetMapping(path = "/objectmapper")
    public CommonResult getNode() {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name", "silent");
        objectNode.put("age", "26");
        objectNode.put("bank savings", "-60000");
        return CommonResult.success(objectNode);
    }
}
