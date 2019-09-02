package com.saivankina.service;

import com.saivankina.entity.Employee;
import com.saivankina.exceptions.EmployeeBadRequestException;
import com.saivankina.exceptions.EmployeeNotFOundException;
import com.saivankina.repository.EmployeeRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(value = SpringRunner.class)
public class EmployeeServiceImplTest {

    @TestConfiguration
    static class EmployeeServiceTestConfiguration{

        @Bean
        public EmployeeService employeeService(){
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService service;

    private List<Employee> employeeList ;

    @Before
    public void setup(){
        Employee emp = new Employee();
        emp.setEmail("sriramGuddati@gmail.com");
        emp.setFirstName("sriram");
        emp.setLastName("Guddati");


        employeeList = Collections.singletonList(emp);
//        employeeList.add(updated);

        Mockito.when(repository.findAll())
                .thenReturn(employeeList);

        Mockito.when(repository.findById(emp.getId()))
                .thenReturn(Optional.of(emp));
        Mockito.when(repository.save(emp))
                .thenReturn(emp);
        Mockito.when(repository.findByEmail(emp.getEmail()))
                .thenReturn(emp);

    }


    @After
    public void cleanUp(){

    }

    @MockBean
    private EmployeeRepository repository;

    @Test
    public void findALL() {
        List<Employee> list = service.findALL();
        Assert.assertEquals("Employee list should match", employeeList,list);
    }

    @Test
    public void findById() {
        Employee emp = service.findById(employeeList.get(0).getId());

        Assert.assertEquals("employees with same id must be same",employeeList.get(0),emp);
    }

    @Test(expected = EmployeeNotFOundException.class)
    public void findByIdNotFound() {
        Employee emp = service.findById("hgvgh ");
        //we are accepting exception
//        Assert.assertEquals("employees with same id must not match",employeeList.get(0),emp);
    }

    @Test
    public void findByEmail() {
        Employee emp = service.findByEmail(employeeList.get(0).getEmail());
        Assert.assertEquals("email should be same",employeeList.get(0),emp);
    }
    @Test(expected = EmployeeNotFOundException.class)
    public void findByEmailFails() {
        Employee emp = service.findByEmail("sairam");
    }

    @Test
    public void createEmployee() {
        Employee newEmployee = service.findById(employeeList.get(0).getId());
        Employee saved = service.createEmployee(newEmployee);
        Assert.assertEquals("employee saved and returned",employeeList.get(0),saved);
    }
    @Test(expected = EmployeeNotFOundException.class)
    public void createEmployeeNotFound() {
        Employee newEmployee = service.findById("asnadbcj");
    }

    @Test
    public void updateEmployee() {
        Employee updated = service.updateEmployee(employeeList.get(0).getId(),employeeList.get(0));
        updated.setEmail("sriram424@gmail.com");
        Assert.assertEquals("Updated the employee",employeeList.get(0).getEmail(),updated.getEmail());
    }
    @Test(expected = EmployeeBadRequestException.class)
    public void updateEmployeeFails() {
        Employee updated = service.updateEmployee("bg vg",employeeList.get(0));
    }

//    @Test
//    public void findByCustom() {
//        Employee emp = service.findByCustom(employeeList.get(0).getEmail());
//        Assert.assertEquals("get employee from email from custom query passed ",employeeList.get(0),emp);
//    }
//
//    @Test
//    public void findByCustom1() {
//        Employee emp = service.findByCustom1(employeeList.get(0).getEmail());
//        Assert.assertEquals("get employee from email from custom query passed ",employeeList.get(0),emp);
//    }
//
//    @Test
//    public void findByCustomUsingNative() {
//        Employee emp = service.findByCustomUsingNative(employeeList.get(0).getEmail());
//        Assert.assertEquals("get employee from email from custom query passed ",employeeList.get(0),emp);
//    }
    @Test
    public void deleteEmployee() {

    }


}