package com.example.TaskManager.dto;

import lombok.Getter;

@Getter
public class CreateUserDto {

    private String roles;

    private String email;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

}
