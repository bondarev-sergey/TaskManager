package com.example.TaskManager.service.impl;

import com.example.TaskManager.dto.UserDto;
import com.example.TaskManager.entity.User;
import com.example.TaskManager.repository.UserRepository;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new MyAuthenticationException("Invalid email or password"));
        if (!passwordEncoder().matches(password, user.getPassword())) {
            throw new MyAuthenticationException("Invalid email or password");
        }
        return toDto(user);
    }

    private UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static class MyAuthenticationException extends AuthenticationException {
        public MyAuthenticationException(String msg) {
            super(msg);
        }
    }

}
