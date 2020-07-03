package com.example.myboot.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: zhouwei
 * @Description:* @Description: 编写一个WebSocketConfig配置类，注入对象ServerEndpointExporter，
 *  * 这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
 * @Date: 2020/7/3 上午11:24
 * @Version: 1.0
 **/
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
