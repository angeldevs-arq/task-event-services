package com.angeldevs.taskservice.application.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record TaskRequestDto(
        @NotBlank String title,
        String description,
        @NotNull @FutureOrPresent LocalDate dueDate,
        @NotNull UUID assignedUserId
) {
}
