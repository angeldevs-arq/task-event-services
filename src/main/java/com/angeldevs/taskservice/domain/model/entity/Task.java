package com.angeldevs.taskservice.domain.model.entity;

import com.angeldevs.taskservice.domain.event.TaskStatusChangedEvent;
import com.angeldevs.taskservice.domain.model.valueobject.TaskId;
import com.angeldevs.taskservice.domain.model.valueobject.TaskStatus;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class Task {

    private final TaskId id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate dueDate;
    private UUID eventId;
    private UUID assignedUserId;

    public Task(TaskId id, String title, String description, TaskStatus status, LocalDate dueDate, UUID eventId, UUID assignedUserId) {
        this.id = Optional.ofNullable(id).orElseGet(TaskId::newId);
        this.title = title;
        this.description = description;
        this.status = Optional.ofNullable(status).orElse(TaskStatus.PENDING);
        this.dueDate = dueDate;
        this.eventId = eventId;
        this.assignedUserId = assignedUserId;
    }

    public TaskId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void rename(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public UUID getEventId() {
        return eventId;
    }

    public UUID getAssignedUserId() {
        return assignedUserId;
    }

    public TaskStatusChangedEvent changeStatus(TaskStatus newStatus) {
        if (!status.canTransitionTo(newStatus)) {
            throw new IllegalStateException("Invalid status transition from " + status + " to " + newStatus);
        }
        TaskStatus previousStatus = this.status;
        this.status = newStatus;
        return new TaskStatusChangedEvent(id, previousStatus, newStatus);
    }

    public void assignTo(UUID userId) {
        this.assignedUserId = userId;
    }

    public void postpone(LocalDate newDueDate) {
        this.dueDate = newDueDate;
    }
}
