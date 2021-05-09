package com.mercadolibre.mutants.errorHandler;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {
    private Date timestamp;
    private int status;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status,  List<String> errors) {
        this.timestamp = new Date();
        this.status = status.value();
        this.message = status.getReasonPhrase();
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String error) {
        this(status, Arrays.asList(error));
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
    
}
