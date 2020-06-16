package com.example.myboot.controller;

import com.example.myboot.bean.Employee;
import com.example.myboot.pojo.Result;
import com.example.myboot.pojo.ResultEnum;
import com.example.myboot.service.EmployeeService;
import com.example.myboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zhouwei
 * @Description: 整合缓存
 * @Date: 2020/6/16 上午2:23
 * @Version: 1.0
 **/
@RestController
@RequestMapping(value = "/cache")
public class CacheController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/{id}")
    public Result getEmployeeById(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResultUtil.success(employee);
    }

    @PutMapping
    public Result updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return ResultUtil.success(ResultEnum.SUCCESS);
    }

    @DeleteMapping("/{id}")
    public Result deleteEmployeeById(@PathVariable("id") Integer id) {
        employeeService.deleteEmployeeById(id);
        return ResultUtil.success(ResultEnum.SUCCESS);
    }

    @GetMapping(value = "/query")
    public Result getEmployeeByName(@RequestParam(value = "name") String name) {
        Employee employee = employeeService.getEmployeeByName(name);
        return ResultUtil.success(employee);
    }

}
