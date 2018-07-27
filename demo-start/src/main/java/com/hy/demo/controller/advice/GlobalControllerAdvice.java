package com.hy.demo.controller.advice;

import com.hy.demo.common.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * GlobalControllerAdvice
 *
 * @author yuhaiyang
 * @date 2018/5/28
 */
@ControllerAdvice
public class GlobalControllerAdvice {


    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public CommonResult processException(Exception e) {
        return CommonResult.error(e.toString());
    }
}
