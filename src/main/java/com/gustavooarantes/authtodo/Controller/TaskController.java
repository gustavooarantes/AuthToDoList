package com.gustavooarantes.authtodo.Controller;

import com.gustavooarantes.authtodo.DTO.TaskRequestDTO;
import com.gustavooarantes.authtodo.DTO.TaskResponseDTO;
import com.gustavooarantes.authtodo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    private String getUsernameFromAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> listTasks() {
        String username = getUsernameFromAuth();
        List<TaskResponseDTO> tasks = taskService.listTasksByUser(username);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        String username = getUsernameFromAuth();
        TaskResponseDTO taskResponseDTO = taskService.createTask(username, taskRequestDTO);
        return ResponseEntity.ok(taskResponseDTO);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskRequestDTO taskRequestDTO) {
        String username = getUsernameFromAuth();
        TaskResponseDTO updatedTask = taskService.updateTask(username, taskId, taskRequestDTO);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        String username = getUsernameFromAuth();
        taskService.deleteTask(username, taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
