package com.angeldevs.taskservice.domain.repository;

import com.angeldevs.taskservice.domain.model.entity.Task;
import com.angeldevs.taskservice.domain.model.valueobject.TaskId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {
    Optional<Task> findById(TaskId id);

    List<Task> findByEventId(UUID eventId);

    Task save(Task task);

    void delete(TaskId id);
}
