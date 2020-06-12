package com.example.myboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: 2020/6/12 下午5:56
 * @Version: 1.0
 **/
@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello-world";
    }

}
