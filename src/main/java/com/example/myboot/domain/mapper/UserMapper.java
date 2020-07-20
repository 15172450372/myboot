package com.example.myboot.domain.mapper;

import com.example.myboot.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: 2020/6/18 下午7:18
 * @Version: 1.0
 **/
@Mapper
public interface UserMapper {

    UserDO findByName(String username);

}
