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
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCreationDate(taskDto.getCreationDate());
        task.setDueDate(taskDto.getDueDate());
        task.setStatus(taskDto.getStatus());

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
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setStatus(taskDto.getStatus());
        task.setAssignee(taskDto.getAssignee() != null ? userRepository.findById(taskDto.getAssignee().getId()).orElseThrow(() -> new EntityNotFoundException("User not found")) : null);

        Task updatedTask = taskRepository.save(task);
        return toTaskDto(updatedTask);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    private TaskDto toTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setCreationDate(task.getCreationDate());
        taskDto.setDueDate(task.getDueDate());
        taskDto.setStatus(task.getStatus());
        taskDto.setAssignee(task.getAssignee() != null ? toUserDto(task.getAssignee()) : null);
        return taskDto;
    }

    private UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
