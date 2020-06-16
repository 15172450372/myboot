package com.example.myboot.service;

import com.example.myboot.bean.Employee;

public interface EmployeeService {

    Employee getEmployeeById(Integer id);

    Employee updateEmployee(Employee employee);

    int deleteEmployeeById(Integer id);

    Employee getEmployeeByName(String name);

}
