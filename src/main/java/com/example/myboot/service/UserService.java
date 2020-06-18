package com.example.myboot.service;

import com.example.myboot.bean.User;
import com.example.myboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByName(String username) {
        return userMapper.findByName(username);
    }

}
