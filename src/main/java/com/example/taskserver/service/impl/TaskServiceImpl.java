package com.example.taskserver.service.impl;

import com.example.taskserver.api.dto.TaskDto;
import com.example.taskserver.api.dto.TaskSummaryDto;
import com.example.taskserver.config.mapstruct.TaskMapper;
import com.example.taskserver.entity.TaskEntity;
import com.example.taskserver.entity.Tag;
import com.example.taskserver.repository.TaskRepository;
import com.example.taskserver.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapper taskMapper;

    @Override
    @Cacheable(value = "tasks", key = "#userId + #pageable.pageNumber")
    public Page<TaskSummaryDto> getTasks(String userId, Tag tag, Pageable pageable) {
        if (tag != null){
            return repository.findByTagAndUserId(tag, userId, pageable).map(taskMapper::toSummaryDto);
        }

        return repository.findByUserId(userId, pageable).map(taskMapper::toSummaryDto);
    }


    @Override
    public TaskDto getById(String  id) {
        return taskMapper.toDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found")));
    }

    @Override
    @CacheEvict(value = "tasks", allEntries = true)
    public TaskDto save(TaskDto taskDto) {
        if (taskDto.getId() != null) {
            return repository.findById(taskDto.getId())
                    .map(existingEntity -> {
                        taskMapper.updateEntityFromDto(taskDto, existingEntity);
                        return taskMapper.toDto(repository.save(existingEntity));
                    })
                    .orElseThrow(() -> new RuntimeException("Note not found"));
        }

        TaskEntity newEntity = taskMapper.toEntity(taskDto);
        return taskMapper.toDto(repository.save(newEntity));
    }

    @Override
    @CacheEvict(value = "tasks", allEntries = true)
    public void delete(String id) {
        repository.deleteById(id);
    }

}
