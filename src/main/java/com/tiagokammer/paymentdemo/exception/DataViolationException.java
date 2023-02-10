package com.tiagokammer.paymentdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DataViolationException extends RuntimeException {

    public DataViolationException() {
        super();
    }

    public DataViolationException(Exception cause) {
        super(cause);
    }

    public DataViolationException(String message) {
        super(message);
    }

    public DataViolationException(String message, Exception cause) {
        super(message, cause);
    }
}
