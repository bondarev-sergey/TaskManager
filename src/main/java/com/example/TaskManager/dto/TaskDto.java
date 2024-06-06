package com.example.TaskManager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime creationDate;

    private LocalDateTime dueDate;

    private String status;

    private UserDto assignee;

}
