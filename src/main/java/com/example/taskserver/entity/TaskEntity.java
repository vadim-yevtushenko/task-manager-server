package com.example.taskserver.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {

    @Id
    private String id;

    @NotBlank(message = "User id is required")
    private String userId;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Task is required")
    private String task;

    @NotBlank(message = "Date is required")
    private LocalDateTime dateTask;

    private Tag tag;

    @CreatedDate
    private LocalDateTime createdDate;

}
