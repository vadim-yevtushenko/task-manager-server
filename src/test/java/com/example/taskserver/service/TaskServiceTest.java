package com.example.taskserver.service;

import com.example.taskserver.api.dto.TaskDto;
import com.example.taskserver.config.mapstruct.TaskMapper;
import com.example.taskserver.entity.TaskEntity;
import com.example.taskserver.repository.TaskRepository;
import com.example.taskserver.res.TestResources;
import com.example.taskserver.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @Mock
    private TaskMapper mapper;

    @InjectMocks
    private TaskServiceImpl service;

//    @Test
//    void getNotes() {
//        Pageable pageable = PageRequest.of(0, 10);
//
//        TaskEntity entity = TestResources.getTestNoteEntity();
//        TaskSummaryDto dto = TestResources.getTestNoteSummaryDto();
//
//        Page<TaskEntity> page = new PageImpl<>(List.of(entity));
//
//        when(repository.findAll(pageable)).thenReturn(page);
//        when(mapper.toSummaryDto(entity)).thenReturn(dto);
//
//        Page<TaskSummaryDto> resultAll = service.getTasksByUserId("", pageable);
//
//        assertEquals(1, resultAll.getContent().size());
//        verify(repository).findAll(pageable);
//
//        Set<Tag> tags = Set.of(Tag.BUSINESS);
//
//
//        when(repository.findByTags(tags, pageable)).thenReturn(page);
//        when(mapper.toSummaryDto(entity)).thenReturn(dto);
//
//        Page<TaskSummaryDto> resultWithTag = service.getTasksByUserId(tags, pageable);
//
//        assertEquals(1, resultWithTag.getContent().size());
//        verify(repository).findByTags(tags, pageable);
//    }

    @Test
    void getNoteById() {
        String id = "1";

        TaskEntity entity = TestResources.getTestTaskEntity();
        TaskDto dto = TestResources.getTestTaskDto();

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        TaskDto result = service.getById(id);

        assertNotNull(result);
        verify(repository).findById(id);

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getById(id));
    }

    @Test
    void saveNote() {
        TaskDto dto = TestResources.getTestTaskDto();
        TaskEntity entity = TestResources.getTestTaskEntity();

        when(repository.findById("1")).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        TaskDto result = service.save(dto);

        assertNotNull(result);
        verify(repository).save(entity);
    }

    @Test
    void deleteNote() {
        String id = "1";

        service.delete(id);

        verify(repository).deleteById(id);
    }
}
