package com.example.myboot.annotation;

import com.example.myboot.annotation.GenderCheck;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author: zhouwei
 * @Description: 参数校验规则：性别不为空且只能为male或female
 * @Date: 2020/6/12 下午8:46
 * @Version: 1.0
 **/
public class GenderCheckValidator implements ConstraintValidator<GenderCheck, String> {

    private String[] list;

    @Override
    public void initialize(GenderCheck constraintAnnotation) {
        this.list = constraintAnnotation.genderList();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s) || (!list[0].equals(s) && !list[1].equals(s))) {
            return false;
        }
        return true;
    }
}
