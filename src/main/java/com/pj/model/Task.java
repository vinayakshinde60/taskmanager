package com.pj.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private String status;
    // Add other fields as needed, e.g., dueDate, priority, etc.
}