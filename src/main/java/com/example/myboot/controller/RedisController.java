package com.example.myboot.controller;

import com.example.myboot.beans.Result;
import com.example.myboot.util.RedisUtil;
import com.example.myboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: 2020/6/16 下午8:44
 * @Version: 1.0
 **/
@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/")
    public Result redisTest() {
        redisUtil.set("hello","tom");
        String str = redisUtil.get("hello");
        return ResultUtil.success(str);
    }

}
