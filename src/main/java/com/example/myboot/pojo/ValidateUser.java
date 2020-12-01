package com.example.myboot.pojo;

import com.example.myboot.annotation.NumberContains;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: 2019/12/14 下午1:46
 * @Version: 1.0
 **/
@Data
public class ValidateUser {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Length(max = 100,message = "用户名过长")
    private String name;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$",message = "手机号格式不对")
    private String mobilNo;

    /**
     * 性别 0:male 1:female
     */
    @NumberContains(value = {0, 1}, message = "性别只允许为1-male;2-female")
    private int sex;
    /**
     * 年龄
     */
    @Min(value = 0, message = "年龄不能小于0")
    private Integer age;
    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

}
