package com.example.myboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: zhouwei
 * @Description: 访问页面
 * @Date: 2020/6/13 下午6:16
 * @Version: 1.0
 **/
@Controller
public class IndexController {

    @GetMapping(value = "/index")
    public String indexPageTest() {
        return "index";
    }

}
