package com.angeldevs.taskservice.application.command;

import java.util.UUID;

public record DeleteTaskCommand(UUID taskId) {
}
