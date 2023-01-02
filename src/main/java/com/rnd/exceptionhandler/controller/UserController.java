package com.rnd.exceptionhandler.controller;

import com.rnd.exceptionhandler.dto.UserRequest;
import com.rnd.exceptionhandler.entity.User;
import com.rnd.exceptionhandler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest userRequest) {
        User user = userService.createUser(userRequest);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getUserList")
    public ResponseEntity<List<User>> getUserList() {
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping("/getUserByName/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(userService.getUserByName(name));
    }
}
