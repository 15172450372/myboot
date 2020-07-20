package com.example.myboot.service;

import com.example.myboot.domain.UserDO;
import com.example.myboot.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserDO findByName(String username) {
        return userMapper.findByName(username);
    }

}
