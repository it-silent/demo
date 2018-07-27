package com.hy.demo.common.enums;

/**
 * StatusEnum
 *
 * @author silent
 * @date 2018/4/22
 */
public enum StatusEnum {

    NORMAL(1),

    DELETE(0);

    public Integer value;

    StatusEnum(Integer value) {
        this.value = value;
    }
}
