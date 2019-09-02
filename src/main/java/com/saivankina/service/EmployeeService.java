package com.saivankina.service;

import com.saivankina.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findALL();
    Employee findById(String empId);
    Employee createEmployee(Employee employee) ;
    Employee updateEmployee(String empId,Employee employee) ;
    void deleteEmployee(String empID);
    Employee findByEmail(String email);
    Employee findByCustom(String email);
    Employee findByCustomUsingNative(String email);
    Employee findByCustom1( String email);
     void deleteAll();
}
