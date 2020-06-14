package com.example.myboot.mapper;

import com.example.myboot.bean.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: zhouwei
 * @Description: mybatis配置文件（.xml）版
 * @Date: 2020/6/14 下午6:44
 * @Version: 1.0
 **/
@Mapper
public interface EmployeeMapper {

    int insertEmployee(Employee employee);

    Employee getEmployeeById(Integer id);

}
