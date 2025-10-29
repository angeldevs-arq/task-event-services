package com.angeldevs.taskservice.infrastructure.rest.controller;

import com.angeldevs.taskservice.application.command.DeleteTaskCommand;
import com.angeldevs.taskservice.application.command.UpdateTaskStatusCommand;
import com.angeldevs.taskservice.application.dto.TaskRequestDto;
import com.angeldevs.taskservice.application.dto.TaskResponseDto;
import com.angeldevs.taskservice.application.dto.UpdateTaskStatusRequest;
import com.angeldevs.taskservice.application.query.GetTasksByEventQuery;
import com.angeldevs.taskservice.application.service.TaskCommandService;
import com.angeldevs.taskservice.application.service.TaskQueryService;
import com.angeldevs.taskservice.infrastructure.rest.assembler.TaskResponseAssembler;
import com.angeldevs.taskservice.infrastructure.rest.controller.response.TaskResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;
    private final TaskResponseAssembler taskResponseAssembler;

    public TaskController(TaskCommandService taskCommandService, TaskQueryService taskQueryService,
                          TaskResponseAssembler taskResponseAssembler) {
        this.taskCommandService = taskCommandService;
        this.taskQueryService = taskQueryService;
        this.taskResponseAssembler = taskResponseAssembler;
    }

    @PostMapping("/events/{eventId}/tasks")
    public ResponseEntity<TaskResponse> createTask(@PathVariable UUID eventId, @Valid @RequestBody TaskRequestDto request) {
        TaskResponseDto dto = taskCommandService.createTask(eventId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponseAssembler.toResponse(dto));
    }

    @GetMapping("/events/{eventId}/tasks")
    public ResponseEntity<List<TaskResponse>> getTasksByEvent(@PathVariable UUID eventId) {
        List<TaskResponseDto> tasks = taskQueryService.getTasksByEvent(new GetTasksByEventQuery(eventId));
        List<TaskResponse> response = tasks.stream().map(taskResponseAssembler::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/tasks/{taskId}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable UUID taskId,
                                                         @Valid @RequestBody UpdateTaskStatusRequest request) {
        TaskResponseDto dto = taskCommandService.updateStatus(new UpdateTaskStatusCommand(taskId, request.status()));
        return ResponseEntity.ok(taskResponseAssembler.toResponse(dto));
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskCommandService.deleteTask(new DeleteTaskCommand(taskId));
        return ResponseEntity.noContent().build();
    }
}
