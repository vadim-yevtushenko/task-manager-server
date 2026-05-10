package com.example.taskserver.service;

import com.example.taskserver.api.dto.TaskDto;
import com.example.taskserver.api.dto.TaskSummaryDto;
import com.example.taskserver.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    Page<TaskSummaryDto> getTasks(String userId, Tag tag, Pageable pageable);

    TaskDto getById(String id);

    TaskDto save(TaskDto taskDto);

    void delete(String id);

}
