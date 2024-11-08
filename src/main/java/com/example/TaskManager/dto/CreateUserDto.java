package com.example.TaskManager.dto;

public record CreateUserDto (
        Long id,
        String username,
        String password,
        String authority,
        String email,
        String firstName,
        String lastName
) {}
