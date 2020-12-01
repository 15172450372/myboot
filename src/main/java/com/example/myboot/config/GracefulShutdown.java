package com.example.myboot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : 周威
 * @version : 1.0
 * @description : 平滑关闭应用(基于Spring Boot 内嵌 Tomcat 容器作为 Web 服务的应用)
 * @date : 2020/9/7 10:50
 **/
@Slf4j
public class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private static final int TIME_OUT = 30;

    private volatile Connector connector;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        //暂停接受外部所有请求
        this.connector.pause();
        //获取 connector 对应的线程池
        Executor executor = this.connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            try {
                log.warn("web应用准备关闭！");
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                //初始化一个关闭任务位于当前待处理完毕的任务之后，并拒绝新的任务提交
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(TIME_OUT, TimeUnit.SECONDS)) {
                    //等待任务完毕后关闭，并只在超时时间内等待
                    log.warn("web应用等待超过最大等待时长：" + TIME_OUT + "秒，将进行强制关闭！");
                    //尝试强制关闭
                    threadPoolExecutor.shutdownNow();
                    if (!threadPoolExecutor.awaitTermination(TIME_OUT, TimeUnit.SECONDS)) {
                        log.error("web应用关闭失败！");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
