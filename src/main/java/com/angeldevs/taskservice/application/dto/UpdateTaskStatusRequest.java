package com.angeldevs.taskservice.application.dto;

import com.angeldevs.taskservice.domain.model.valueobject.TaskStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskStatusRequest(@NotNull TaskStatus status) {
}
