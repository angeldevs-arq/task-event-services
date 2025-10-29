package com.angeldevs.taskservice.application.dto;

import com.angeldevs.taskservice.domain.model.valueobject.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

public record TaskResponseDto(
        UUID id,
        String title,
        String description,
        TaskStatus status,
        LocalDate dueDate,
        UUID eventId,
        UUID assignedUserId
) {
}
