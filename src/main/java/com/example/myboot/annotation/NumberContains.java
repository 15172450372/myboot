package com.example.myboot.annotation;

import com.example.myboot.config.NumberContainsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义性别参数校验注解，性别只允许为male/female
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NumberContainsValidator.class)
public @interface NumberContains {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] value() default {};

}
