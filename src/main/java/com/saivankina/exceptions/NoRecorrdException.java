package com.saivankina.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT)
public class NoRecorrdException extends RuntimeException {
    public NoRecorrdException(String msg) {
        super(msg);
    }
}
