package com.example.myboot.domain.mapper;

import com.example.myboot.domain.EmployeeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: zhouwei
 * @Description: mybatis配置文件（.xml）版
 * @Date: 2020/6/14 下午6:44
 * @Version: 1.0
 **/
@Mapper
public interface EmployeeMapper {

    int insertEmployee(EmployeeDO employee);

    EmployeeDO getEmployeeById(Integer id);

    int updateEmployee(EmployeeDO employee);

    int deleteEmployeeById(Integer id);

    EmployeeDO getEmployeeByName(String name);

}
