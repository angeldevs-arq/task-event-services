package com.angeldevs.taskservice.application.command;

import java.util.UUID;

public record DeleteTaskCommand(UUID taskId) {

    public DeleteTaskCommand {
        taskId = java.util.Objects.requireNonNull(taskId, "Task id is required");
    }
}
