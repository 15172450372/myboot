package com.example.myboot.domain.mapper;

import com.example.myboot.domain.PermissionDO;
import com.example.myboot.domain.RoleDO;
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

    List<PermissionDO> selectListByUserId(Integer id);

    List<RoleDO> selectRoleListByUserId(Integer id);

}
