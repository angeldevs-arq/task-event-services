package com.angeldevs.taskservice.domain.model.valueobject;

public enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED;

    public boolean canTransitionTo(TaskStatus newStatus) {
        if (this == COMPLETED) {
            return false;
        }
        if (this == PENDING) {
            return newStatus == IN_PROGRESS || newStatus == COMPLETED;
        }
        return newStatus == COMPLETED;
    }
}
