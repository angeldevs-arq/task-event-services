package com.angeldevs.taskservice.application.command;

import com.angeldevs.taskservice.domain.model.valueobject.TaskStatus;

import java.util.UUID;

public record UpdateTaskStatusCommand(UUID taskId, TaskStatus status) {
}
