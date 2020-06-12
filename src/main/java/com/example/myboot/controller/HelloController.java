package com.example.myboot.controller;

import com.example.myboot.pojo.Result;
import com.example.myboot.pojo.ValidateUser;
import com.example.myboot.util.ResultUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.myboot.pojo.ResultEnum.SUCCESS;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: 2020/6/12 下午5:56
 * @Version: 1.0
 **/
@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public Result hello() {
        return ResultUtil.success("hello-world");
    }

    /**
     * 测试参数校验 validator
     * @param user
     * @return
     */
    @PostMapping(value = "/validate")
    public Result parameterValidateTest(@RequestBody @Validated ValidateUser user, BindingResult bingingresult) {
        if (bingingresult.hasErrors()) {
            String errorMes = bingingresult.getAllErrors().get(0).getDefaultMessage();
            return ResultUtil.error(errorMes);
        }
        return ResultUtil.success(SUCCESS);
    }
}
