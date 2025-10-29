package com.angeldevs.taskservice.infrastructure.persistence.repository;

import com.angeldevs.taskservice.domain.model.entity.Task;
import com.angeldevs.taskservice.domain.model.valueobject.TaskId;
import com.angeldevs.taskservice.infrastructure.persistence.entity.TaskJpaEntity;

final class TaskEntityMapper {

    private TaskEntityMapper() {
    }

    static TaskJpaEntity toEntity(Task task) {
        TaskJpaEntity entity = new TaskJpaEntity();
        entity.setId(task.getId().value());
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setStatus(task.getStatus());
        entity.setDueDate(task.getDueDate());
        entity.setEventId(task.getEventId());
        entity.setAssignedUserId(task.getAssignedUserId());
        return entity;
    }

    static Task toDomain(TaskJpaEntity entity) {
        return new Task(new TaskId(entity.getId()), entity.getTitle(), entity.getDescription(), entity.getStatus(),
                entity.getDueDate(), entity.getEventId(), entity.getAssignedUserId());
    }
}
