package com.example.myboot.config.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: zhouwei
 * @Description: 自定义的密码加密类,此处为明文，可根据需要改为自己的加密方式
 * @Date: 2020/6/18 下午7:42
 * @Version: 1.0
 **/
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    /**
     * 加密
     *
     * @param charSequence 登录用户输入的密码
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    /**
     * 密码比对
     *
     * @param charSequence 登录用户输入的密码
     * @param s 数据库加密后的密码
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
