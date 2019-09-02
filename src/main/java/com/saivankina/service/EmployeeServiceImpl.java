package com.saivankina.service;

import com.saivankina.entity.Employee;
import com.saivankina.exceptions.EmployeeBadRequestException;
import com.saivankina.exceptions.EmployeeNotFOundException;
import com.saivankina.exceptions.NoRecorrdException;
import com.saivankina.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> findALL() {
//        List<Employee> listOFEmployees = (List<Employee>) employeeRepository.findAll();
//        if (listOFEmployees.size() == 0) {
//            //return 204
//            throw new NoRecorrdException("The List is empty ");
//        }

        return (List<Employee>) employeeRepository.findAll();

    }

    @Transactional
    public Employee findById(String empId) {
//        Employee emp =  employeeRepository.findById(empId);
        Optional<Employee> emp = employeeRepository.findById(empId);
        if (!emp.isPresent()) {
            throw new EmployeeNotFOundException("Employee with Id " + empId + " Not found ");
        }
//        since emp is optional use emp.get()
        return emp.get();
    }

    @Transactional
    public Employee findByEmail(String email) {
        System.out.println("inside service layer of email");
        Employee emp = employeeRepository.findByEmail(email);
        if (emp == null) {
            throw new EmployeeNotFOundException("Employee with Id " + email + " Not found ");
        }
        return emp;
    }

    @Transactional
    public Employee createEmployee(Employee employee) {
        Employee emp = employeeRepository.save(employee);
        if (emp == null) {
            throw new EmployeeBadRequestException("Employee" + employee + " cannot be created because of bad request");
        }
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(String empId, Employee employee) {
        Optional<Employee> emp = employeeRepository.findById(empId);
        if (!emp.isPresent()) {
            throw new EmployeeBadRequestException("Employee" + employee + " cannot be created because of bad request");
        }
        emp.get().setLastName(employee.getLastName());
        emp.get().setFirstName(employee.getFirstName());
        emp.get().setEmail(employee.getEmail());

        return employeeRepository.save(emp.get());
    }

    @Transactional
    public void deleteEmployee(String empId) {
        Employee e = findById(empId);
        employeeRepository.delete(e);
    }
    @Transactional
    public void deleteAll() {
        List<Employee> e = findALL();
        employeeRepository.deleteAll(e);
    }

    public Employee findByCustom(String email) {
        Employee emp = employeeRepository.findByCustom(email);
//        query.setParameter("paramEmail",email);
        System.out.println("Employe sis "+emp);
        if (emp == null) {
            throw new EmployeeBadRequestException("Employee" + emp + " cannot be created because of bad request");
        }
        return emp;
    }


    public Employee findByCustom1(String email) {
        Employee emp = employeeRepository.findByCustom1(email);
//        query.setParameter("paramEmail",email);
        System.out.println("Employe sis " + emp);
        if (emp == null) {
            throw new EmployeeBadRequestException("Employee" + emp + " cannot be created because of bad request");
        }
        return emp;
    }

    public Employee findByCustomUsingNative(String email){
         Employee emp = employeeRepository.findByCustomUsingNative(email);
//        query.setParameter("paramEmail",email);
         System.out.println("Employe sis "+emp);
         if (emp == null) {
             throw new EmployeeBadRequestException("Employee" + emp + " cannot be created because of bad request");
         }
         return emp;
     }

}
