package com.example.taskserver.controller;

import com.example.taskserver.api.controller.TaskRestController;
import com.example.taskserver.api.dto.TaskDto;
import com.example.taskserver.api.dto.TaskSummaryDto;
import com.example.taskserver.entity.Tag;
import com.example.taskserver.res.TestResources;
import com.example.taskserver.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskRestController.class)
public class TaskRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getTaskTest() throws Exception {

        TaskSummaryDto summary = TestResources.getTestTaskSummaryDto();

        List<TaskSummaryDto> list = List.of(summary);
        Page<TaskSummaryDto> page = new PageImpl<>(list);

        when(taskService.getTasks(any(),any(Tag.class), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/tasks")
                        .param("userId", "1")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value("1"))
                .andExpect(jsonPath("$.content[0].title").value("TaskDto"))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.number").value(0));


        Page<TaskSummaryDto> emptyPage = new PageImpl<>(Collections.emptyList());
        when(taskService.getTasks(any(), any(Tag.class), any(Pageable.class))).thenReturn(emptyPage);

        mockMvc.perform(get("/tasks")
                        .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isEmpty())
                .andExpect(jsonPath("$.totalElements").value(0));
    }

    @Test
    void getTaskByIdTest() throws Exception {
        TaskDto taskDto = TestResources.getTestTaskDto();

        when(taskService.getById("1")).thenReturn(taskDto);

        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("TaskDto"))
                .andExpect(jsonPath("$.task").value("Task - is just, a task."));
    }

    @Test
    void createTaskTest() throws Exception {
        TaskDto taskDto = TestResources.getTestTaskDto();

        when(taskService.save(any(TaskDto.class))).thenReturn(taskDto);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("TaskDto"));

        TaskDto invalidNote = TestResources.getTestTaskDto();
        invalidNote.setTitle("");

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidNote)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateTaskTest() throws Exception {

        String urlId = "1";
        TaskDto taskDto = TestResources.getTestTaskDto();

        when(taskService.save(any(TaskDto.class))).thenReturn(taskDto);

        mockMvc.perform(put("/tasks/" + urlId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(urlId))
                .andExpect(jsonPath("$.title").value("TaskDto"))
                .andExpect(jsonPath("$.task").value("Task - is just, a task."));


        String wrongUrlId = "2";

        assertThrows(jakarta.servlet.ServletException.class, () -> {
            mockMvc.perform(put("/tasks/" + wrongUrlId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(taskDto)));
        });
    }

    @Test
    void deleteTaskTest() throws Exception {
        String urlId = "1";

        mockMvc.perform(delete("/tasks/" + urlId))
                .andExpect(status().isOk());

        verify(taskService).delete(urlId);
    }

}
