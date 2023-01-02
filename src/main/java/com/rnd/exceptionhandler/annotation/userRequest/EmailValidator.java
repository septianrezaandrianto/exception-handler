package com.rnd.exceptionhandler.annotation.userRequest;

import com.rnd.exceptionhandler.exception.UserException;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {

    @SneakyThrows
    public boolean isValid(String email, ConstraintValidatorContext ctx) {
        Pattern regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);

        if(!regex.matcher(email).find()) {
            UserException exception = new UserException();
            exception.setResponseCode("400200");
            exception.setResponseMessage("Invalid format email");
            throw exception;
        }
        return true;
    }
}
