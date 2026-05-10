package com.example.taskserver.api.controller;

import com.example.taskserver.api.dto.TaskDto;
import com.example.taskserver.api.dto.TaskSummaryDto;
import com.example.taskserver.entity.Tag;
import com.example.taskserver.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskRestController {

    private final TaskService service;

    @GetMapping
    public Page<TaskSummaryDto> getTasksByUserId(@RequestParam String userId,
                                                 @RequestParam(required = false) Tag tag,
                                         @PageableDefault(size = 10,
                                                 sort = "dateTask",
                                                 direction = Sort.Direction.ASC)
                                         Pageable pageable) {

        return service.getTasks(userId, tag, pageable);
    }

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public TaskDto create(@RequestBody @Valid TaskDto task) {
        return service.save(task);
    }

    @PutMapping("/{id}")
    public TaskDto update(@PathVariable String id, @RequestBody @Valid TaskDto task) {
        if (!id.equals(task.getId())){
            throw new RuntimeException("Id does not match");
        }
        return service.save(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

}
