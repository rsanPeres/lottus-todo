package com.lottus.todo.core.dto;

import com.lottus.todo.core.domain.TodoEntity;
import com.lottus.todo.core.enums.PriorityEnum;
import com.lottus.todo.core.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private UUID id;

    private String title;

    private LocalDate dueDate;

    private LocalDate executionDate;

    private PriorityEnum priority;

    private StatusEnum status;

    private Boolean highlight;

    public TodoEntity toEntity(){
        return new TodoEntity(id, title, dueDate, executionDate, priority, status, highlight);
    }

    public TodoDto(TodoEntity entity){
        id = entity.getId();
        title = entity.getTitle();
        dueDate = entity.getDueDate();
        executionDate = entity.getExecutionDate();
        priority = entity.getPriority();
        status = entity.getStatus();
        highlight = entity.getHighlight();
    }
}
