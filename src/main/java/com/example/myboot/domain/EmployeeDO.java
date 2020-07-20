package com.example.myboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zhouwei
 * @Description: 数据库实体映射类-employee
 * @Date: 2020/6/14 下午6:41
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDO implements Serializable {

    private Integer id;
    private String name;
    private String email;
    private String gender;
    private Integer did;

    public EmployeeDO(String name, String email, String gender, Integer dId) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.did = dId;
    }
}
