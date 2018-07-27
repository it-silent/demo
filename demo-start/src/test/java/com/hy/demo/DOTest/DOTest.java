package com.hy.demo.DOTest;

import lombok.Data;

import java.util.Date;

/**
 * DOTest
 *
 * @author silent
 * @date 2018/5/25
 */
@Data
public class DOTest {

//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date start;

    public static void main(String[] args) {
        DOTest doTest = new DOTest();
        doTest.setStart(new Date());
//        System.err.println(JSON.toJSONString(doTest));
    }
}
