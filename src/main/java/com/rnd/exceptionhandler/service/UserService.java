package com.rnd.exceptionhandler.service;

import com.rnd.exceptionhandler.dto.UserRequest;
import com.rnd.exceptionhandler.entity.User;
import com.rnd.exceptionhandler.exception.NotFoundException;
import com.rnd.exceptionhandler.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .gender(userRequest.getGender())
                .age(userRequest.getAge())
                .address(userRequest.getAddress())
                .build();
        return userRepository.save(user);
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @SneakyThrows
    public User getUserByName(String name) {
        User user = userRepository.getUserByName(name);
        if(user != null) {
            return userRepository.getUserByName(name);
        } else {
            throw errorMapping();
        }

    }

    private NotFoundException errorMapping() {
        NotFoundException exception = new NotFoundException();
        exception.setResponseCode("404600");
        exception.setResponseMessage("User not found");
        return exception;
    }

}
