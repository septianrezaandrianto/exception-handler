package com.rnd.exceptionhandler.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotFoundException extends Exception {

    private String responseCode;
    private String responseMessage;

    public NotFoundException(String message) {
        super(message);
    }
}
