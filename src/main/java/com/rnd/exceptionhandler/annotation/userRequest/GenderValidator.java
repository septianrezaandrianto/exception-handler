package com.rnd.exceptionhandler.annotation.userRequest;

import com.rnd.exceptionhandler.exception.UserException;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<GenderValidation, String> {

    @SneakyThrows
    public boolean isValid(String gender, ConstraintValidatorContext ctx) {
        if(!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
            UserException exception = new UserException();
            exception.setResponseCode("400400");
            exception.setResponseMessage("Invalid format gender (M/F)");
            throw exception;
        }

        return true;
    }
}
