package com.hy.demo.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hy.demo.base.BaseTest;
import com.hy.demo.service.stt.SttService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ObjectMapperTest
 *          objectMapper
 *
 * @author  Yu.HaiYang
 * @date    2018/7/13
 */
public class ObjectMapperTest extends BaseTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SttService sttService;

    @Test
    public void test() {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("1", "一无是处");
        objectNode.put("2", "二月红前来求药");
        objectNode.put("3", "三心二意");
        objectNode.put("4", "四两拨千斤");
        objectNode.put("5", (String) null);
        System.err.println(objectNode);
        System.err.println(sttService.findById(1L));
    }
}
