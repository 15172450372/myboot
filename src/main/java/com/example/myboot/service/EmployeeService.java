package com.example.myboot.service;

import com.example.myboot.bean.Employee;
import com.example.myboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouwei
 * @Description:
 *   @Cacheable:缓存数据，@CachePut:更新缓存数据,@CacheEvict：清除缓存数据
 * @Date: 2020/6/16 上午2:20
 * @Version: 1.0
 **/
@Service
@CacheConfig(cacheNames = "employee")
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * @Cacheable:缓存数据，若结果存在于缓存中则不执行方法
     * keyGenerator：键生成策略；
     * condition：条件成立则缓存；（第一个参数大于1就缓存结果）
     * unless：否定缓存；（第一个参数等于2结果不缓存）
     * sync:是否异步；
     * @param id
     * @return
     */
    //@Cacheable(cacheNames = "employee", keyGenerator = "CustomerKeyGenerator", condition = "#a0>1", unless = "#a0==2")
    @Cacheable(key = "#id")
    public Employee getEmployeeById(Integer id) {
        System.out.println("查询id为“”"+id+"的员工");
        return employeeMapper.getEmployeeById(id);
    }

    /**
     * 调用方法，更新缓存
     *
     * @param employee
     * @return
     */
    @CachePut(key = "#employee.id")
    public Employee updateEmployee(Employee employee) {
        System.out.println("更新缓存【"+employee.toString()+"]");
        employeeMapper.updateEmployee(employee);
        return employee;
    }

    /**
     * 缓存清除
     * allEntries:清除所有缓存数据
     * beforeInvocation：方法执行钱清除缓存，默认在方法之后
     * @param id
     * @return
     */
    @CacheEvict(allEntries = true, beforeInvocation = true)
    public int deleteEmployeeById(Integer id) {
        System.out.println("删除员工：" + id);
        return employeeMapper.deleteEmployeeById(id);
    }

    /**
     * @Caching组合注解
     * @param name
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(key = "#name", unless = "#result==null")  ////根据name缓存
            },
            put = {
                    @CachePut(key = "#result.id", unless = "#result==null"),  //根据id缓存
                    @CachePut(key = "#result.email", unless = "#result==null"),  //根据email缓存
            }
    )

    public Employee getEmployeeByName(String name) {
        System.out.println("根据名称查询员工");
        Employee employee = employeeMapper.getEmployeeByName(name);
        return employee;
    }
}
