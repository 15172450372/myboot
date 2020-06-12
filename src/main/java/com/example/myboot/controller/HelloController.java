package com.example.myboot.controller;

import com.example.myboot.pojo.Result;
import com.example.myboot.pojo.ValidateUser;
import com.example.myboot.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger logger =  LoggerFactory.getLogger(HelloController.class);

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

    @GetMapping("/log")
    public void logTest() {
        logger.trace("跟踪日志");
        logger.debug("调试日志");
        logger.info("一般日志");
        logger.warn("警告日志");
        logger.error("错误日志");
    }
}
