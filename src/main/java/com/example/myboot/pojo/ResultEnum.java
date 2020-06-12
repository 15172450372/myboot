package com.example.myboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举，统一管理返回码和返回信息
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {

    SUCCESS(1,"成功"),
    FAILURE(0,"失败"),
    UNKNOW_ERROR(-1,"未知错误");

    private Integer code;
    private String message;

}
