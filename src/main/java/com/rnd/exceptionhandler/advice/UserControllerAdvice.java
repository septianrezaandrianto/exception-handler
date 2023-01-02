package com.rnd.exceptionhandler.advice;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rnd.exceptionhandler.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class UserControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> invalidMethodArgumentHandler(MethodArgumentNotValidException ex) {
        Map<String, Object> errorMap = new HashMap<>();

        List<String> errorList = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorList.add(error.getField() + ":" +error.getDefaultMessage());
        });

        errorMap.put("responseCode", HttpStatus.BAD_REQUEST.toString().split("\\s")[0]);
        errorMap.put("responseMessage", HttpStatus.BAD_REQUEST.toString().split("\\s")[1]);
        errorMap.put("detail", errorList);
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserException.class)
    public Map<String, Object> notFoundException(UserException ex) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("responseCode", HttpStatus.INTERNAL_SERVER_ERROR.toString().split("\\s")[0]);
        errorMap.put("responseMessage", HttpStatus.INTERNAL_SERVER_ERROR.toString().split("\\s")[1]);
        errorMap.put("detail", ex.getMessage());
        return errorMap;
    }
}
