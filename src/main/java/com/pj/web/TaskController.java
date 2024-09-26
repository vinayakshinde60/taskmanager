package com.pj.web;
import com.pj.model.Task;
import com.pj.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tasks") // Adjust the base path as needed
public class TaskController {
    private final TaskService taskService;
 public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
  
  

    @GetMapping("/find/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable String id) {
            return ResponseEntity.ok(taskService.getTaskById(id));
    }
 
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
         return ResponseEntity.ok(taskService.updateTask(task));
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}