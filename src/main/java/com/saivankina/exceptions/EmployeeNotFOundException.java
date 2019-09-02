package com.saivankina.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EmployeeNotFOundException extends RuntimeException {
    public EmployeeNotFOundException(String msg){
        super(msg);
    }
}
