package com.example.TaskManager.service;

import com.example.TaskManager.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto createTask(TaskDto taskDto);

    TaskDto getTaskById(Integer id);

    List<TaskDto> getAllTasks();

    TaskDto updateTask(Integer id, TaskDto taskDto);

    void deleteTask(Integer id);

}
