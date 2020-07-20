package com.example.myboot.service;

import com.example.myboot.domain.PermissionDO;
import com.example.myboot.domain.RoleDO;
import com.example.myboot.domain.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: 2020/6/18 下午7:28
 * @Version: 1.0
 **/
@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<PermissionDO> selectListByUserId(Integer id){
        return permissionMapper.selectListByUserId(id);
    }
    public List<RoleDO> selectRoleListByUserId(Integer id){
        return permissionMapper.selectRoleListByUserId(id);
    }

}
