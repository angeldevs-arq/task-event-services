package com.angeldevs.taskservice.infrastructure.persistence.repository;

import com.angeldevs.taskservice.domain.model.entity.Task;
import com.angeldevs.taskservice.domain.model.valueobject.TaskId;
import com.angeldevs.taskservice.domain.repository.TaskRepository;
import com.angeldevs.taskservice.infrastructure.persistence.entity.TaskJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskJpaRepository taskJpaRepository;

    public TaskRepositoryImpl(TaskJpaRepository taskJpaRepository) {
        this.taskJpaRepository = taskJpaRepository;
    }

    @Override
    public Optional<Task> findById(TaskId id) {
        return taskJpaRepository.findById(id.value()).map(TaskEntityMapper::toDomain);
    }

    @Override
    public List<Task> findByEventId(UUID eventId) {
        return taskJpaRepository.findByEventId(eventId).stream().map(TaskEntityMapper::toDomain).toList();
    }

    @Override
    public Task save(Task task) {
        TaskJpaEntity entity = TaskEntityMapper.toEntity(task);
        TaskJpaEntity saved = taskJpaRepository.save(entity);
        return TaskEntityMapper.toDomain(saved);
    }

    @Override
    public void delete(TaskId id) {
        taskJpaRepository.deleteById(id.value());
    }

}
