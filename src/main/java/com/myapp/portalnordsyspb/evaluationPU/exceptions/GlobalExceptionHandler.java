package com.myapp.portalnordsyspb.evaluationPU.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> globalExceptionHandler(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AreaNotFoundException.class)
    public Map<String, String> areaNotFound(AreaNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error: ", ex.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmailAlreadyExistException.class)
    public Map<String, String> emailAlreadyExist(EmailAlreadyExistException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error: ", ex.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomUserNotFoundException.class)
    public Map<String, String> usernameNotFound(CustomUserNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error: ", ex.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RefreshTokenExpiredException.class)
    public Map<String, String> refreshTokenExpired(RefreshTokenExpiredException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error: ", ex.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public Map<String, String> refreshTokenNotFound(RefreshTokenNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error: ", ex.getMessage());
        return error;
    }
}
