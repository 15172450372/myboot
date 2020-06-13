package com.example.myboot.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: zhouwei
 * @Description: 跨域
 * @Date: 2020/6/13 下午5:43
 * @Version: 1.0
 **/
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域访问的路径
                .allowedOrigins("*") // 允许跨域访问的源
                .allowedMethods("GET","POST","PUT", "DELETE","OPTIONS") // 允许请求方法
                .allowCredentials(true)  // 是否发送cookie
                .allowedHeaders("*") // 允许头部设置
                .maxAge(3600); // 预检间隔时间
    }

}
