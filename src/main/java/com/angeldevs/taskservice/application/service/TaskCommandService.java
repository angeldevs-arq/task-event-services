package com.angeldevs.taskservice.application.service;

import com.angeldevs.taskservice.application.command.CreateTaskCommand;
import com.angeldevs.taskservice.application.command.DeleteTaskCommand;
import com.angeldevs.taskservice.application.command.UpdateTaskStatusCommand;
import com.angeldevs.taskservice.application.dto.TaskRequestDto;
import com.angeldevs.taskservice.application.dto.TaskResponseDto;
import com.angeldevs.taskservice.domain.model.entity.Task;
import com.angeldevs.taskservice.domain.model.valueobject.TaskId;
import com.angeldevs.taskservice.domain.model.valueobject.TaskStatus;
import com.angeldevs.taskservice.domain.repository.TaskRepository;
import com.angeldevs.taskservice.shared.exception.NotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class TaskCommandService {

    private final TaskRepository taskRepository;

    public TaskCommandService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponseDto createTask(CreateTaskCommand command) {
        Task task = new Task(null, command.title(), command.description(), TaskStatus.PENDING, command.dueDate(),
                command.eventId(), command.assignedUserId());
        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    public TaskResponseDto createTask(UUID eventId, TaskRequestDto request) {
        CreateTaskCommand command = new CreateTaskCommand(eventId, request.title(), request.description(), request.dueDate(), request.assignedUserId());
        return createTask(command);
    }

    public TaskResponseDto updateStatus(UpdateTaskStatusCommand command) {
        Task task = taskRepository.findById(new TaskId(command.taskId()))
                .orElseThrow(() -> new NotFoundException("Task not found"));
        task.changeStatus(command.status());
        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    public void deleteTask(DeleteTaskCommand command) {
        TaskId taskId = new TaskId(command.taskId());
        taskRepository.delete(taskId);
    }

    private TaskResponseDto mapToResponse(Task task) {
        return new TaskResponseDto(
                task.getId().value(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate(),
                task.getEventId(),
                task.getAssignedUserId()
        );
    }
}
