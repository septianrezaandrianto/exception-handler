package com.rnd.exceptionhandler.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserException extends Exception {

    private String responseCode;
    private String responseMessage;

    public UserException(String message) {
        super(message);
    }

}
