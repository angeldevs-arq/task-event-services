package com.angeldevs.taskservice.infrastructure.persistence.entity;

import com.angeldevs.taskservice.domain.model.valueobject.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class TaskJpaEntity {

    @Id
    @Column(name = "id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatus status;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "event_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID eventId;

    @Column(name = "assigned_user_id", columnDefinition = "BINARY(16)")
    private UUID assignedUserId;

    public TaskJpaEntity() {
    }

    public TaskJpaEntity(UUID id, String title, String description, TaskStatus status, LocalDate dueDate, UUID eventId, UUID assignedUserId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.eventId = eventId;
        this.assignedUserId = assignedUserId;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public void setAssignedUserId(UUID assignedUserId) {
        this.assignedUserId = assignedUserId;
    }
}
