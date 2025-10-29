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
}
