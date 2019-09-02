package com.saivankina.controller;

import com.saivankina.entity.Employee;
import com.saivankina.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
//@ResponseBody

@CrossOrigin("*")
@RestController
@RequestMapping(value="/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
//    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> findALL(){
        return employeeService.findALL();
    }

    @GetMapping("{id}")
//    @RequestMapping( method = RequestMethod.GET,value = "{id}")
    public Employee findById(@PathVariable("id") String empId){
        return  employeeService.findById(empId);
//        return null;
    }
    @GetMapping(value ="{email}",headers = "key=val")
    public Employee findByEmail(@PathVariable("email")  String email){
        System.out.println(email);
        return  employeeService.findByEmail(email);
//        return null;
    }


//    @PostMapping("/")PostMapping
//    @RequestMapping( method = RequestMethod.POST)

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)

    public Employee createEmployee( @Valid @RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }


//    @PutMapping("/{id}")
//    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @PutMapping(value = "{id}",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Employee updateEmployee(@PathVariable("id") String empId,@RequestBody Employee employee) {
        return employeeService.updateEmployee(empId,employee);
    }

//    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable("id") String id){
        employeeService.deleteEmployee(id);
    }


    // exception handling for validation
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class,SQLIntegrityConstraintViolationException.class})
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex, SQLIntegrityConstraintViolationException exe) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

//        exe.getBindingResult().getAllErrors().forEach((errore) -> {
//            String fieldName = exe.getSQLState();
//            String errorMessage = exe.getMessage();
//            errors.put(fieldName, errorMessage);
//        });
        return errors;
    }

}

