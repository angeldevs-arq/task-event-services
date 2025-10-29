package com.angeldevs.taskservice.infrastructure.rest.controller.response;

import com.angeldevs.taskservice.domain.model.valueobject.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

public record TaskResponse(
        UUID id,
        String title,
        String description,
        TaskStatus status,
        LocalDate dueDate,
        UUID eventId,
        UUID assignedUserId
) {
}
