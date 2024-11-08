package com.example.TaskManager.dto;

public record UserDto (
        Long id,
        String username,
        String authority,
        String email,
        String firstName,
        String lastName
) {}
