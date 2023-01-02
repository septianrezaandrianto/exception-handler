package com.rnd.exceptionhandler.service;

import com.rnd.exceptionhandler.dto.UserRequest;
import com.rnd.exceptionhandler.entity.User;
import com.rnd.exceptionhandler.exception.UserException;
import com.rnd.exceptionhandler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<User> getUserById(String id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if(!user.isEmpty()) {
            return user;
        } else {
            throw new UserException("User with id " + id + " not found!");
        }

    }
}
