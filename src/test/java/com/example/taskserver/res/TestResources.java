package com.example.taskserver.res;

import com.example.taskserver.api.dto.TaskDto;
import com.example.taskserver.api.dto.TaskSummaryDto;
import com.example.taskserver.entity.Tag;
import com.example.taskserver.entity.TaskEntity;

import java.time.LocalDateTime;

public class TestResources {

    public static TaskEntity getTestTaskEntity(){
        return new TaskEntity("1","1", "Task", "Task - is just, a task.", LocalDateTime.now(),
                Tag.PERSONAL, LocalDateTime.now());
    }

    public static TaskDto getTestTaskDto(){
        return new TaskDto("1", "1", "TaskDto", "Task - is just, a task.",
                Tag.PERSONAL, LocalDateTime.now(), LocalDateTime.now());
    }

    public static TaskSummaryDto getTestTaskSummaryDto(){
        return new TaskSummaryDto("1", "1", "TaskDto", Tag.PERSONAL, LocalDateTime.now(), LocalDateTime.now());
    }

}
