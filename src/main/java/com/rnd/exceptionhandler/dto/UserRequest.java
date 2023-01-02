package com.rnd.exceptionhandler.dto;

import com.rnd.exceptionhandler.annotation.userRequest.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NameValidation
    private String name;
    @EmailValidation
    private String email;
    @PhoneNumberValidation
    private String phoneNumber;
    @GenderValidation
    private String gender;
    @AgeValidation
    private Integer age;
    private String address;

}
