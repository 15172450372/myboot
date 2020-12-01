package com.example.myboot.annotation;

import java.lang.annotation.*;

/**
 * @author 周威
 * @version v1.0.0
 * @description
 * @date 2020/10/10 16:41
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NoRepeatCommit {

    /**
     * 指定时间内不可重复提交,单位毫秒
     */
    long timeout() default 500 ;

}