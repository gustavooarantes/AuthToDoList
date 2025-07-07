package com.gustavooarantes.authtodo.Service;

import com.gustavooarantes.authtodo.Config.Exception.TaskNotFoundException;
import com.gustavooarantes.authtodo.Config.Exception.UserNotFoundException;
import com.gustavooarantes.authtodo.DTO.TaskRequestDTO;
import com.gustavooarantes.authtodo.DTO.TaskResponseDTO;
import com.gustavooarantes.authtodo.Model.TaskModel;
import com.gustavooarantes.authtodo.Model.UserModel;
import com.gustavooarantes.authtodo.Repository.TaskRepository;
import com.gustavooarantes.authtodo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    private TaskResponseDTO toResponseDTO(TaskModel taskModel) {
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(taskModel.getId().intValue());
        taskResponseDTO.setDescription(taskModel.getDescription());
        taskResponseDTO.setStatus(taskModel.isStatus());
        taskResponseDTO.setCreatedAt(taskModel.getCreatedAt());
        return taskResponseDTO;
    }

    public List<TaskResponseDTO> listTasksByUser(String username) {
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        List<TaskModel> tasks = taskRepository.findByUser(user);

        return tasks.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TaskResponseDTO createTask(String username, TaskRequestDTO taskRequestDTO) {
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        TaskModel task = TaskModel.builder()
                .description(taskRequestDTO.getDescription())
                .status(taskRequestDTO.isStatus())
                .user(user)
                .build();

        TaskModel savedTask = taskRepository.save(task);

        return toResponseDTO(savedTask);
    }

    public TaskResponseDTO updateTask(String username, Long taskId, TaskRequestDTO taskRequestDTO) {
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        TaskModel task = taskRepository.findByIdAndUser(taskId, user)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.isStatus());

        TaskModel updatedTask = taskRepository.save(task);

        return toResponseDTO(updatedTask);
    }

    public void deleteTask(String username, Long taskId) {
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        TaskModel task = taskRepository.findByIdAndUser(taskId, user)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        taskRepository.delete(task);
    }
}
