package com.lottus.todo.core.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lottus.todo.core.dto.TodoDto;
import com.lottus.todo.core.enums.PriorityEnum;
import com.lottus.todo.core.enums.StatusEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record TodoRequest(UUID id, @NotNull @NotEmpty @Size(max = 255) String title,
                          @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dueDate,
                          @JsonFormat(pattern = "dd/MM/yyyy") LocalDate executionDate,
                          @NotNull PriorityEnum priority,
                          @NotNull StatusEnum status, Boolean highlight) {
    public TodoDto toDto(){
        return TodoDto.builder().id(id).dueDate(dueDate).executionDate(executionDate).highlight(highlight)
                .priority(priority).status(status).title(title).build();
    }
}
