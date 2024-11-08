package com.example.TaskManager.dto;

import com.example.TaskManager.model.TaskStatus;
import java.time.LocalDateTime;

public record TaskDto (
        Long id,
        String title,
        String description,
        LocalDateTime creationDate,
        LocalDateTime dueDate,
        TaskStatus status
){}
