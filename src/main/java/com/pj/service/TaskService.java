package com.pj.service;

import com.pj.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getAllTasks();
    Optional<Task> getTaskById(String id);
    Task createTask(Task task);
    Task updateTask(Task task);
    void deleteTask(String id);
}
