package com.example.TaskManager.service.impl;

import com.example.TaskManager.dto.TaskDto;
import com.example.TaskManager.dto.UserDto;
import com.example.TaskManager.entity.Task;
import com.example.TaskManager.entity.User;
import com.example.TaskManager.repository.TaskRepository;
import com.example.TaskManager.repository.UserRepository;
import com.example.TaskManager.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.title());
        task.setDescription(taskDto.description());
        task.setCreationDate(taskDto.creationDate());
        task.setDueDate(taskDto.dueDate());
        task.setStatus(taskDto.status());

        Task savedTask = taskRepository.save(task);
        return toTaskDto(savedTask);
    }

    public TaskDto getTaskById(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        return toTaskDto(task);
    }

    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(this::toTaskDto).collect(Collectors.toList());
    }

    public TaskDto updateTask(Integer id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        task.setTitle(taskDto.title());
        task.setDescription(taskDto.description());
        task.setCreationDate(taskDto.creationDate());
        task.setDueDate(taskDto.dueDate());
        task.setStatus(taskDto.status());
//        task.setAssignee(taskDto.getAssignee() != null ? userRepository.findById(taskDto.getAssignee().getId()).orElseThrow(() -> new EntityNotFoundException("User not found")) : null);

        Task updatedTask = taskRepository.save(task);
        return toTaskDto(updatedTask);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    private TaskDto toTaskDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreationDate(),
                task.getDueDate(),
                task.getStatus()
        );
//        taskDto.setAssignee(task.getAssignee() != null ? toUserDto(task.getAssignee()) : null);
    }

}
