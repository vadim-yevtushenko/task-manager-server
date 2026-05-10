package com.example.taskserver.api.dto;

import com.example.taskserver.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSummaryDto {

    private String id;

    private String userId;

    private String title;

    private Tag tag;

    private LocalDateTime dateTask;

    private LocalDateTime createdDate;
}
