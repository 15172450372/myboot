package com.example.myboot.mapper;

import com.example.myboot.bean.Permission;
import com.example.myboot.bean.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: 2020/6/18 下午7:26
 * @Version: 1.0
 **/
@Mapper
public interface PermissionMapper {

    List<Permission> selectListByUserId(Integer id);

    List<Role> selectRoleListByUserId(Integer id);

}
