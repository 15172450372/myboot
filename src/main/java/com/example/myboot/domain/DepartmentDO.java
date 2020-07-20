package com.example.myboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhouwei
 * @Description: 数据库实体映射类-department
 * @Date: 2020/6/14 下午6:46
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDO {

    private Integer id;
    private String name;

}
