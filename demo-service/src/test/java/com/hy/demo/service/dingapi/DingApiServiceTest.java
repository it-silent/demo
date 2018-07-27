package com.hy.demo.service.dingapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.demo.common.utils.TimeUtils;
import com.hy.demo.service.base.BaseServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * DingApiServiceTest
 *
 * @author silent
 * @date 2018/4/12
 */
public class DingApiServiceTest extends BaseServiceTest{

    @Autowired
    private DingApiService dingApiService;

    private ObjectMapper objectMapper;

    @Test
    public void send() {
        String address = "https://oapi.dingtalk.com/robot/send?access_token=66c8f7db3c4fd25934f490cc01dec291f0d2b777d1eb6000a64d39d7d3b34088";
        String msg = "hello it`s me again " + TimeUtils.format(new Date());
//      String result = dingApiService.sendByConnector(address, msg);
//      Boolean aBoolean = dingApiService.send(address, msg);
        String result = dingApiService.send(address, msg);
        System.err.println(result);
//      System.exit(0);
    }
}
