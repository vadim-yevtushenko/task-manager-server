package com.example.taskserver.repository;

import com.example.taskserver.entity.TaskEntity;
import com.example.taskserver.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Set;

public interface TaskRepository extends MongoRepository<TaskEntity, String> {

    Page<TaskEntity> findByTagAndUserId(Tag tag, String userId, Pageable pageable);

    Page<TaskEntity>findByUserId(String userId, Pageable pageable);

}
