package com.example.TaskManager.controller;

import com.example.TaskManager.dto.CreateUserDto;
import com.example.TaskManager.dto.UserDto;
import com.example.TaskManager.service.impl.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final UserDetailsServiceImpl userService;

    public RegistrationController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody CreateUserDto userDto) {
        UserDto registeredUser = userService.createUser(userDto);
        return ResponseEntity.created(URI.create("/users/" + registeredUser.username()))
                .body(registeredUser);
    }

}
