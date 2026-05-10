package com.example.taskserver.repository;

import com.example.taskserver.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity findByEmail(String email);

}
