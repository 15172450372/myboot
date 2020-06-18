package com.example.myboot.util;

import com.example.myboot.pojo.Result;
import com.example.myboot.pojo.ResultEnum;


/**
 * @Author: zhouwei
 * @Description: 统一返回接口工具类
 * @Date: 2019/12/14 下午2:00
 * @Version: 1.0
 **/
public class ResultUtil {

    public static Result<Object> success(Object object) {
        Result<Object> result = success(ResultEnum.SUCCESS);
        result.setData(object);
        return result;
    }

    public static Result<Object> success() {
        return success(null);
    }

    public static Result<Object> success(Integer code, String msg) {
        Result<Object> result = new Result<>();
        result.setCode(200);
        result.setMessage(msg);
        return result;
    }

    public static Result<Object> success(ResultEnum resultEnum) {
        Result<Object> result = new Result<>();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }

    public static Result<Object> error(Object object) {
        Result<Object> result = error(ResultEnum.PARAMETER_ERROR);
        result.setData(object);
        return result;
    }

    public static Result<Object> error(Integer code, String msg) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public static Result<Object> error(ResultEnum resultEnum) {
        Result<Object> result = new Result<>();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }


}
