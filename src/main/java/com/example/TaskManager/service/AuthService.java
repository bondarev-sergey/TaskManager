package com.example.TaskManager.service;

import com.example.TaskManager.dto.UserDto;

public interface AuthService {

    UserDto authenticate(String email, String password);

}
