package com.example.myboot.config;

import com.example.myboot.annotation.NumberContains;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * @Author: zhouwei
 * @Description: 参数校验规则：性别不为空且只能为male或female
 * @Date: 2020/6/12 下午8:46
 * @Version: 1.0
 **/
public class NumberContainsValidator implements ConstraintValidator<NumberContains, Integer> {

    private int[] list;

    @Override
    public void initialize(NumberContains sex) {
        this.list = sex.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (list.length == 0 || Objects.isNull(value)) {
            return false;
        }
        for (int i : list) {
            if (value.equals(i)) {
                return true;
            }
        }
        return false;
    }
}
