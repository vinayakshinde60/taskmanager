package com.pj.repo;

import com.pj.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
    // You can add custom query methods here if needed
}