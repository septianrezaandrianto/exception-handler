package com.rnd.exceptionhandler.controller;

import com.rnd.exceptionhandler.dto.UserRequest;
import com.rnd.exceptionhandler.entity.User;
import com.rnd.exceptionhandler.exception.UserException;
import com.rnd.exceptionhandler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Validated @RequestBody UserRequest userRequest) {
        User user = userService.createUser(userRequest);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getUserList")
    public ResponseEntity<List<User>> getUserList() {
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable(value = "id") String id) throws UserException {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
