package com.angeldevs.taskservice.domain.model.entity;

import com.angeldevs.taskservice.domain.model.valueobject.TaskId;
import com.angeldevs.taskservice.domain.model.valueobject.TaskStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskTest {

    @Test
    void changeStatusShouldUpdateStatusWhenTransitionIsAllowed() {
        Task task = new Task(TaskId.newId(), "Task", "Description", TaskStatus.PENDING, LocalDate.now(), UUID.randomUUID(), UUID.randomUUID());

        task.changeStatus(TaskStatus.IN_PROGRESS);

        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    void changeStatusShouldThrowWhenTransitionIsNotAllowed() {
        Task task = new Task(TaskId.newId(), "Task", "Description", TaskStatus.COMPLETED, LocalDate.now(), UUID.randomUUID(), UUID.randomUUID());

        assertThrows(IllegalStateException.class, () -> task.changeStatus(TaskStatus.IN_PROGRESS));
    }
}
