package com.example.TaskManager.service;

import com.example.TaskManager.dto.CreateUserDto;
import com.example.TaskManager.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerUser(CreateUserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);

}
