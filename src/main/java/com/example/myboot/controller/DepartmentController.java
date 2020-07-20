package com.example.myboot.controller;

import com.example.myboot.domain.DepartmentDO;
import com.example.myboot.domain.mapper.DepartmentMapper;
import com.example.myboot.pojo.Result;
import com.example.myboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import static com.example.myboot.pojo.ResultEnum.*;

/**
 * @Author: zhouwei
 * @Description: 整合mybatis
 * @Date: 2020/6/14 下午6:57
 * @Version: 1.0
 **/
@RestController
@RequestMapping(value = "/dept")
public class DepartmentController {

    @Autowired
    private DepartmentMapper departmentMapper;

    @PostMapping
    public Result insertDepartment(@RequestParam(value = "name", required = true) String name) {
        DepartmentDO department = new DepartmentDO();
        department.setName(name);
        int resultCount = departmentMapper.insertDepartment(department);
        if (resultCount > 0) {
            return ResultUtil.success("ok");
        }
        return ResultUtil.error(FAILURE);
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteDepartmentById(@PathVariable(value = "id") Integer id) {
        departmentMapper.deleteDepartmentById(id);
        return ResultUtil.success(SUCCESS);
    }

    @GetMapping(value = "/{id}")
    public Result getDepartmentById(@PathVariable("id") Integer id) {
        DepartmentDO department = departmentMapper.getDepartmentById(id);
        return ResultUtil.success(department);
    }

    @PutMapping
    public Result updateDepartment(@RequestParam(value = "id") Integer id,
                                   @RequestParam(value = "name") String name) {
        if (id < 1 || StringUtils.isEmpty(name)) {
            return ResultUtil.error(PARAMETER_ERROR);
        }
        departmentMapper.updateDepartment(new DepartmentDO(id, name));
        return ResultUtil.success(SUCCESS);
    }

}
