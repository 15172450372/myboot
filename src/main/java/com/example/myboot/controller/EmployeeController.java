package com.example.myboot.controller;

import com.example.myboot.domain.EmployeeDO;
import com.example.myboot.domain.mapper.EmployeeMapper;
import com.example.myboot.pojo.Result;
import com.example.myboot.pojo.ResultEnum;
import com.example.myboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.myboot.pojo.ResultEnum.FAILURE;
import static com.example.myboot.pojo.ResultEnum.SUCCESS;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: 2020/6/14 下午9:28
 * @Version: 1.0
 **/
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping(value = "/{id}")
    public Result getEmployeeById(@PathVariable(value = "id") Integer id) {
        if (id < 1) {
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        EmployeeDO employeeById = employeeMapper.getEmployeeById(id);
        return ResultUtil.success(employeeById);
    }

    @PostMapping
    public Result insertEmployee(@RequestBody EmployeeDO employee) {
        int resultCount = employeeMapper.insertEmployee(employee);
        if (resultCount == 0) {
            ResultUtil.error(FAILURE);
        }
        return ResultUtil.success(SUCCESS);
    }

}
