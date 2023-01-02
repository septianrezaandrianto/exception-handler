package com.rnd.exceptionhandler.annotation.userRequest;

import com.rnd.exceptionhandler.exception.UserException;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValidation, String> {

    @SneakyThrows
    public boolean isValid(String phoneNumber, ConstraintValidatorContext ctx) {
        Pattern regex = Pattern.compile("^\\d{12}$");

        if(!regex.matcher(phoneNumber).find()) {
            UserException exception = new UserException();
            exception.setResponseCode("400300");
            exception.setResponseMessage("Phone number must be 12 character");
            throw exception;
        }
        return true;
    }
}
