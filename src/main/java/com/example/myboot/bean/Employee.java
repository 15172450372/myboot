package com.example.myboot.bean;

import lombok.Data;

/**
 * @Author: zhouwei
 * @Description: 数据库实体映射类-employee
 * @Date: 2020/6/14 下午6:41
 * @Version: 1.0
 **/
@Data
public class Employee {

    private Integer id;
    private String name;
    private String email;
    private String gender;
    private Integer dId;

}
