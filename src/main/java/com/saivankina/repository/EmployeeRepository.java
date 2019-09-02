package com.saivankina.repository;


import com.saivankina.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,String> {

    Employee findByEmail(String email);
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT emp FROM Employee emp WHERE emp.email = :Email")
    Employee findByCustom(@Param("Email") String email);

    @Query("SELECT emp FROM Employee emp WHERE emp.email = ?1")
    Employee findByCustom1( String email);

    @Query(value = "SELECT * FROM Employee emp WHERE emp.email = ?1", nativeQuery = true)
    Employee findByCustomUsingNative(String email);



}
