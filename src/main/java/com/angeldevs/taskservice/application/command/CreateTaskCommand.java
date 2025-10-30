package com.angeldevs.taskservice.application.command;

import java.time.LocalDate;
import java.util.UUID;

public record CreateTaskCommand(
        UUID eventId,
        String title,
        String description,
        LocalDate dueDate,
        UUID assignedUserId
) {

    public CreateTaskCommand {
        eventId = java.util.Objects.requireNonNull(eventId, "Event id is required");
        title = java.util.Objects.requireNonNull(title, "Task title is required");
        dueDate = java.util.Objects.requireNonNull(dueDate, "Task due date is required");
    }
}
