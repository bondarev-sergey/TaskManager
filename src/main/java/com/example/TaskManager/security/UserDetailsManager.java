package com.example.TaskManager.security;

import com.example.TaskManager.dto.CreateUserDto;
import com.example.TaskManager.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserDetailsManager extends UserDetailsService {
    UserDto createUser(CreateUserDto createUserDto);
    UserDto getUserByUsername(String username);
    List<UserDto> getAllUsers();
    UserDto updateUser(String username, UserDto userDto);
    void deleteUser(String username);
    void changePassword(String oldPassword, String newPassword);
    boolean userExists(String username);
}
