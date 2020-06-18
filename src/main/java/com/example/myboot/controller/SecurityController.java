package com.example.myboot.controller;

import com.example.myboot.pojo.Result;
import com.example.myboot.service.UserService;
import com.example.myboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zhouwei
 * @Description: 权限控制
 * @Date: 2020/6/13 下午6:16
 * @Version: 1.0
 **/
@RestController
@RequestMapping(value = "/security")
public class SecurityController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public Result hello(){
        return ResultUtil.success(200,"hello");
    }

    //该方法我们在security配置类中指定了admin角色才可以访问
    @RequestMapping("/admin")
    public Result admin(){
        return ResultUtil.success(200,"admin");
    }

    //当用户具有select权限时才可以访问该方法
    @PreAuthorize("hasAuthority('select')")
    @RequestMapping("/select")
    public Result select(){
        return ResultUtil.success(200,"select");
    }

    //当用户具有insert权限时才可以访问该方法
    @PreAuthorize("hasAuthority('insert')")
    @RequestMapping("/insert")
    public Result insert(){
        return ResultUtil.success(200,"insert");
    }

    //当用户具有update权限时才可以访问该方法
    @PreAuthorize("hasAuthority('update')")
    @RequestMapping("/update")
    public Result update(){
        return ResultUtil.success(200,"update");
    }

    //如果访问需要登录的接口，如果用户还没登录就会跳转到这个接口
    @RequestMapping("/login_page")
    public Result root(){
        return ResultUtil.error(401,"unLogin!");
    }

    @RequestMapping("/getUser")
    public Result getUser(){
        //获取我们正在登陆的用户信息
        //注意这里的User是security的
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return ResultUtil.success(userService.findByName(username));
    }

}
