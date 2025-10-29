package com.angeldevs.taskservice.domain.model.valueobject;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record TaskId(UUID value) implements Serializable {

    public TaskId {
        Objects.requireNonNull(value, "Task id cannot be null");
    }

    public static TaskId newId() {
        return new TaskId(UUID.randomUUID());
    }
}
