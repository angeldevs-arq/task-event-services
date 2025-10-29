package com.angeldevs.taskservice.infrastructure.persistence.repository;

import com.angeldevs.taskservice.infrastructure.persistence.entity.TaskJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskJpaRepository extends JpaRepository<TaskJpaEntity, UUID> {
    List<TaskJpaEntity> findByEventId(UUID eventId);
}
