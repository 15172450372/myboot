package com.example.myboot.beans;

import lombok.Data;

/**
 * @Author: zhouwei
 * @Description: 统一返回接口
 * @Date: 2019/12/14 下午1:57
 * @Version: 1.0
 **/
@Data
public class Result<T> {

    //返回码
    private Integer code;

    //提示信息
    private String message;

    //返回具体内容
    private T data;

}
