package com.example.TaskManager.dto;

import com.example.TaskManager.model.TaskStatus;
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

    private TaskStatus status;

    private UserDto assignee;

}
