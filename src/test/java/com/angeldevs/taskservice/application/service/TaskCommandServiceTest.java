package com.angeldevs.taskservice.application.service;

import com.angeldevs.taskservice.application.command.CreateTaskCommand;
import com.angeldevs.taskservice.application.command.UpdateTaskStatusCommand;
import com.angeldevs.taskservice.application.dto.TaskResponseDto;
import com.angeldevs.taskservice.domain.model.entity.Task;
import com.angeldevs.taskservice.domain.model.valueobject.TaskId;
import com.angeldevs.taskservice.domain.model.valueobject.TaskStatus;
import com.angeldevs.taskservice.domain.repository.TaskRepository;
import com.angeldevs.taskservice.shared.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskCommandServiceTest {

    private TaskRepository taskRepository;
    private TaskCommandService taskCommandService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskCommandService = new TaskCommandService(taskRepository);
    }

    @Test
    void createTaskShouldPersistAndReturnDto() {
        CreateTaskCommand command = new CreateTaskCommand(UUID.randomUUID(), "Title", "Description", LocalDate.now(), UUID.randomUUID());
        Task savedTask = new Task(TaskId.newId(), command.title(), command.description(), TaskStatus.PENDING, command.dueDate(), command.eventId(), command.assignedUserId());
        when(taskRepository.save(org.mockito.Mockito.any(Task.class))).thenReturn(savedTask);

        TaskResponseDto response = taskCommandService.createTask(command);

        assertThat(response.title()).isEqualTo("Title");
        verify(taskRepository).save(org.mockito.Mockito.any(Task.class));
    }

    @Test
    void updateStatusShouldChangeStatus() {
        UUID taskId = UUID.randomUUID();
        Task existingTask = new Task(new TaskId(taskId), "Title", "Description", TaskStatus.PENDING, LocalDate.now(), UUID.randomUUID(), UUID.randomUUID());
        when(taskRepository.findById(new TaskId(taskId))).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(existingTask);

        TaskResponseDto response = taskCommandService.updateStatus(new UpdateTaskStatusCommand(taskId, TaskStatus.IN_PROGRESS));

        assertThat(response.status()).isEqualTo(TaskStatus.IN_PROGRESS);
    }

    @Test
    void updateStatusShouldThrowWhenTaskNotFound() {
        UUID taskId = UUID.randomUUID();
        when(taskRepository.findById(new TaskId(taskId))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> taskCommandService.updateStatus(new UpdateTaskStatusCommand(taskId, TaskStatus.IN_PROGRESS)));
    }
}
