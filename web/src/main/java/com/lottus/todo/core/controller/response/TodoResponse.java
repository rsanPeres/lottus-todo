package com.lottus.todo.core.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lottus.todo.core.dto.TodoDto;
import com.lottus.todo.core.enums.PriorityEnum;
import com.lottus.todo.core.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
    private UUID id;

    private String title;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dueDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate executionDate;

    private PriorityEnum priority;

    private StatusEnum status;

    private Boolean highlight;

    public TodoResponse(TodoDto dto){
        id = dto.getId();
        title = dto.getTitle();
        dueDate = dto.getDueDate();
        executionDate = dto.getExecutionDate();
        priority = dto.getPriority();
        status = dto.getStatus();
        highlight = dto.getHighlight();
    }
}
