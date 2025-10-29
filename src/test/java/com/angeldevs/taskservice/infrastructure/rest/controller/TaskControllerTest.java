package com.angeldevs.taskservice.infrastructure.rest.controller;

import com.angeldevs.taskservice.application.dto.TaskRequestDto;
import com.angeldevs.taskservice.application.dto.TaskResponseDto;
import com.angeldevs.taskservice.application.service.TaskCommandService;
import com.angeldevs.taskservice.application.service.TaskQueryService;
import com.angeldevs.taskservice.domain.model.valueobject.TaskStatus;
import com.angeldevs.taskservice.infrastructure.rest.assembler.TaskResponseAssembler;
import com.angeldevs.taskservice.infrastructure.rest.controller.response.TaskResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskCommandService taskCommandService;

    @MockBean
    private TaskQueryService taskQueryService;

    @MockBean
    private TaskResponseAssembler taskResponseAssembler;

    @Test
    void createTaskShouldReturnCreatedTask() throws Exception {
        UUID eventId = UUID.randomUUID();
        TaskRequestDto request = new TaskRequestDto("Title", "Description", LocalDate.now(), UUID.randomUUID());
        TaskResponseDto responseDto = new TaskResponseDto(UUID.randomUUID(), "Title", "Description", TaskStatus.PENDING,
                request.dueDate(), eventId, request.assignedUserId());
        TaskResponse response = new TaskResponse(responseDto.id(), responseDto.title(), responseDto.description(),
                responseDto.status(), responseDto.dueDate(), responseDto.eventId(), responseDto.assignedUserId());

        when(taskCommandService.createTask(eq(eventId), any(TaskRequestDto.class))).thenReturn(responseDto);
        when(taskResponseAssembler.toResponse(responseDto)).thenReturn(response);

        mockMvc.perform(post("/api/v1/events/{eventId}/tasks", eventId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Title"));
    }

    @Test
    void getTasksShouldReturnList() throws Exception {
        UUID eventId = UUID.randomUUID();
        TaskResponseDto dto = new TaskResponseDto(UUID.randomUUID(), "Title", "Description", TaskStatus.PENDING,
                LocalDate.now(), eventId, UUID.randomUUID());
        TaskResponse response = new TaskResponse(dto.id(), dto.title(), dto.description(), dto.status(), dto.dueDate(),
                dto.eventId(), dto.assignedUserId());

        when(taskQueryService.getTasksByEvent(any())).thenReturn(List.of(dto));
        when(taskResponseAssembler.toResponse(dto)).thenReturn(response);

        mockMvc.perform(get("/api/v1/events/{eventId}/tasks", eventId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(dto.id().toString()));
    }
}
