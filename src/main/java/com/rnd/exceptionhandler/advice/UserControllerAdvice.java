package com.rnd.exceptionhandler.advice;

import com.rnd.exceptionhandler.exception.NotFoundException;
import com.rnd.exceptionhandler.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserException.class})
    public Map<String, Object> userExceptionHandler(UserException ex) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("responseCode", ex.getResponseCode());
        errorMap.put("responseMessage", ex.getResponseMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public Map<String, Object> userNotFoundHandler(NotFoundException ex) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("responseCode", ex.getResponseCode());
        errorMap.put("responseMessage", ex.getResponseMessage());
        return errorMap;
    }

}
