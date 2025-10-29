package com.angeldevs.taskservice.domain.event;

import com.angeldevs.taskservice.domain.model.valueobject.TaskId;
import com.angeldevs.taskservice.domain.model.valueobject.TaskStatus;

public record TaskStatusChangedEvent(TaskId taskId, TaskStatus previousStatus, TaskStatus currentStatus) {
}
