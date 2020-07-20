package com.example.myboot.domain.mapper;

import com.example.myboot.domain.DepartmentDO;
import org.apache.ibatis.annotations.*;

/**
 * @Author: zhouwei
 * @Description: mybatis注解版
 * @Date: 2020/6/14 下午6:50
 * @Version: 1.0
 **/
@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    public DepartmentDO getDepartmentById(Integer id);

    @Delete("delete from department where id = #{id}")
    public int deleteDepartmentById(Integer id);

    @Insert("insert into department(name) values(#{name})")
    public int insertDepartment(DepartmentDO department);

    @Update("update department set name = #{name}")
    public int updateDepartment(DepartmentDO department);

}
