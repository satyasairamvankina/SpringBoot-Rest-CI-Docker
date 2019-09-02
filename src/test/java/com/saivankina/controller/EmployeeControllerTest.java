package com.saivankina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saivankina.entity.Employee;
import com.saivankina.repository.EmployeeRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest") //asking to use this env file which follow main application file except the overridden one's
public class EmployeeControllerTest {

    //used for api calls in test env like postman
    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    private List<Employee> employeeList;

    @Before
    public void setup(){
        Employee emp = new Employee();
        emp.setId("ramId");
        emp.setEmail("sriramGuddati@gmail.com");
        emp.setFirstName("sriram");
        emp.setLastName("Guddati");
        employeeRepository.save(emp);

//        Employee emp1 = new Employee();
//        emp1.setId("gopiId");
//        emp1.setEmail("gopinath@gmail.com");
//        emp1.setFirstName("gopi");
//        emp1.setLastName("manne");
//        employeeRepository.save(emp1);

    }

    @After
    public void cleanup(){
        employeeRepository.deleteAll();
    }

    @Test
    public void findALL() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/employees")).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("sriramGuddati@gmail.com")));
    }

    @Test
    public void findById() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/employees/ramId"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("sriramGuddati@gmail.com")));

    }
    @Test
    public void findByIdFails() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/employees/asds")).
                andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void findByEmail() throws Exception {
    }

    @Test
    public void createEmployee() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Employee emp = new Employee();
        emp.setId("gopiId");
        emp.setEmail("gopinath@gmail.com");
        emp.setFirstName("gopi");
        emp.setLastName("manne");

        mvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsBytes(emp)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("gopinath@gmail.com")));

        mvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }
    @Test
   public void createEmployee400() throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        Employee emp = new Employee();
//        emp.setId("gopiId");
//        emp.setEmail("sriramGuddati@gmail.com");
//        emp.setFirstName("gopi");
//        emp.setLastName("manne");
//
//        mvc.perform(MockMvcRequestBuilders.post("/employees")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(mapper.writeValueAsBytes(emp)))
//                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateEmployee() throws Exception {
    }

    @Test
    public void deleteEmployee() throws Exception {
    }
}