package com.example.TaskManager.controller;

import com.example.TaskManager.dto.CreateUserDto;
import com.example.TaskManager.dto.UserDto;
import com.example.TaskManager.service.impl.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserDetailsServiceImpl userService;

    public UserController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.created(URI.create("/users/" + createdUser.username())).body(createdUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        UserDto userDto = userService.getUserByUsername(username);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(username, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}
