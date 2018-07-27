package com.hy.demo.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * CommonResult
 *
 * @author wb-yhy282733
 */
@Setter
@Getter
@ToString
public class CommonResult {

    private Object data;

    private boolean success;

    private String msg;

    public static CommonResult error(String msg) {
        return new CommonResult(false, null, msg);
    }

    public static CommonResult success(Object data) {
        return new CommonResult(true, data, null);
    }
    public static CommonResult success(Object data, String msg) {
        return new CommonResult(true, data, msg);
    }

    public static CommonResult success() {
        return new CommonResult(true, null, null);
    }

    public CommonResult(boolean success, Object data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }





    public void tesss() {
        success();
        success();
        success();
        success();
        success();
        success();
        success();
        success();
        success();
        success();
        success();
        success();
        success();
        success();
        success();
    }
}
