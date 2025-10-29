package com.angeldevs.taskservice.application.service;

import com.angeldevs.taskservice.application.dto.TaskResponseDto;
import com.angeldevs.taskservice.application.query.GetTasksByEventQuery;
import com.angeldevs.taskservice.domain.model.entity.Task;
import com.angeldevs.taskservice.domain.repository.TaskRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskQueryService {

    private final TaskRepository taskRepository;

    public TaskQueryService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskResponseDto> getTasksByEvent(GetTasksByEventQuery query) {
        List<Task> tasks = taskRepository.findByEventId(query.eventId());
        return tasks.stream()
                .map(task -> new TaskResponseDto(
                        task.getId().value(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus(),
                        task.getDueDate(),
                        task.getEventId(),
                        task.getAssignedUserId()
                ))
                .toList();
    }
}
