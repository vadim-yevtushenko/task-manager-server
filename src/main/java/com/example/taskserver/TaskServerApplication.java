package com.example.taskserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TaskServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskServerApplication.class, args);
    }

}
