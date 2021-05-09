package com.mercadolibre.mutants.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    // Error handle for @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> validationErrors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(x -> x.getField() + ": " + x.getDefaultMessage())
            .collect(Collectors.toList());

        ApiError body = new ApiError(status, validationErrors);

        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAllUncaughtException(Exception ex, WebRequest request) {
        ApiError body = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        
        return new ResponseEntity<>(body, new HttpHeaders(), body.getStatus());
    }
}