package com.rnd.exceptionhandler.annotation.userRequest;

import com.rnd.exceptionhandler.exception.UserException;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<AgeValidation, Integer> {

    @SneakyThrows
    public boolean isValid(Integer age, ConstraintValidatorContext ctx) {
        if(age < 18 || age > 63) {
            UserException exception = new UserException();
            exception.setResponseCode("400500");
            exception.setResponseMessage("Invalid age >= 18 and <= 63");
            throw exception;
        }
        return true;
    }
}
