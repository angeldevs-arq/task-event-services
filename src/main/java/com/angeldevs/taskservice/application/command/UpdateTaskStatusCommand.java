package com.angeldevs.taskservice.application.command;

import com.angeldevs.taskservice.domain.model.valueobject.TaskStatus;

import java.util.UUID;

public record UpdateTaskStatusCommand(UUID taskId, TaskStatus status) {

    public UpdateTaskStatusCommand {
        taskId = java.util.Objects.requireNonNull(taskId, "Task id is required");
        status = java.util.Objects.requireNonNull(status, "Task status is required");
    }
}
