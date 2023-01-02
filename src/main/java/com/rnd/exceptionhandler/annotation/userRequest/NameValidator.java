package com.rnd.exceptionhandler.annotation.userRequest;

import antlr.StringUtils;
import com.rnd.exceptionhandler.entity.User;
import com.rnd.exceptionhandler.exception.UserException;
import com.rnd.exceptionhandler.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<NameValidation, String> {

    @Autowired
    private UserRepository userRepository;

    @SneakyThrows
    public boolean isValid(String name, ConstraintValidatorContext ctx) {
        User user = userRepository.getUserByName(name);
        Pattern regex = Pattern.compile("^[a-zA-Z]+$");

        if(Objects.isNull(name)) {
            UserException exception = new UserException();
            exception.setResponseCode("400100");
            exception.setResponseMessage("Name shouldn't be null");
            throw exception;
        }

        if(ObjectUtils.isEmpty(name.trim())) {
            UserException exception = new UserException();
            exception.setResponseCode("400101");
            exception.setResponseMessage("Name shouldn't be empty string");
            throw exception;
        }

        if(name.length() > 20) {
            UserException exception = new UserException();
            exception.setResponseCode("400102");
            exception.setResponseMessage("Name must be less 20 characters");
            throw exception;
        }

        if(!regex.matcher(name).find()) {
            UserException exception = new UserException();
            exception.setResponseCode("400103");
            exception.setResponseMessage("Name must be alphanumeric");
            throw exception;
        }

        if(user != null) {
            UserException exception = new UserException();
            exception.setResponseCode("400104");
            exception.setResponseMessage("Name has been registered");
            throw exception;
        }

        return true;
    }
}
