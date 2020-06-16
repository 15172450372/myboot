package com.example.myboot.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * @Author: zhouwei
 * @Description: 缓存键的生成策略
 * @Date: 2020/6/16 上午2:57
 * @Version: 1.0
 **/
@Configuration
public class CustomerKeyGenerator {

    @Bean("CustomerKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                return method.getName() + "[" + Arrays.asList(objects).toString() + "]";
            }
        };
    }

}
