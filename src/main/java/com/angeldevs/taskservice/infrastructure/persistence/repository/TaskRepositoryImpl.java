package com.angeldevs.taskservice.infrastructure.persistence.repository;

import com.angeldevs.taskservice.domain.model.entity.Task;
import com.angeldevs.taskservice.domain.model.valueobject.TaskId;
import com.angeldevs.taskservice.domain.repository.TaskRepository;
import com.angeldevs.taskservice.infrastructure.persistence.entity.TaskJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskJpaRepository taskJpaRepository;

    public TaskRepositoryImpl(TaskJpaRepository taskJpaRepository) {
        this.taskJpaRepository = taskJpaRepository;
    }

    @Override
    public Optional<Task> findById(TaskId id) {
        return taskJpaRepository.findById(id.value()).map(this::toDomain);
    }

    @Override
    public List<Task> findByEventId(UUID eventId) {
        return taskJpaRepository.findByEventId(eventId).stream().map(this::toDomain).toList();
    }

    @Override
    public Task save(Task task) {
        TaskJpaEntity entity = toEntity(task);
        TaskJpaEntity saved = taskJpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public void delete(TaskId id) {
        taskJpaRepository.deleteById(id.value());
    }

    private TaskJpaEntity toEntity(Task task) {
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

    private Task toDomain(TaskJpaEntity entity) {
        return new Task(new TaskId(entity.getId()), entity.getTitle(), entity.getDescription(), entity.getStatus(),
                entity.getDueDate(), entity.getEventId(), entity.getAssignedUserId());
    }
}
