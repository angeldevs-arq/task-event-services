package com.angeldevs.taskservice.infrastructure.rest.assembler;

import com.angeldevs.taskservice.application.dto.TaskResponseDto;
import com.angeldevs.taskservice.infrastructure.rest.controller.response.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskResponseAssembler {

    public TaskResponse toResponse(TaskResponseDto dto) {
        return new TaskResponse(
                dto.id(),
                dto.title(),
                dto.description(),
                dto.status(),
                dto.dueDate(),
                dto.eventId(),
                dto.assignedUserId()
        );
    }
}
