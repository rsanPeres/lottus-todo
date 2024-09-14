package com.lottus.todo.core.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lottus.todo.core.dto.ScheduleDto;
import com.lottus.todo.core.dto.TodoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponse {
    private UUID id;

    private Collection<TodoResponse> todos;

    public ScheduleResponse(ScheduleDto dto){
        id = dto.getId();
        todos = dto.getTodos().stream().map(TodoResponse::new).toList();
    }
}
