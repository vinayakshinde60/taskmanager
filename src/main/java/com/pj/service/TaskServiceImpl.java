package com.pj.service;

import java.util.List;
import java.util.Optional;
import com.pj.model.Task;
import com.pj.repo.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
   
    private final TaskRepository taskRepository;

      public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
  
    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

   
 
    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

}
