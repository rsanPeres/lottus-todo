package com.lottus.todo.core.controller.request;

import com.lottus.todo.core.dto.ScheduleDto;
import com.lottus.todo.core.dto.TodoDto;

import java.util.List;
import java.util.UUID;

public record ScheduleRequest(UUID id, List<TodoRequest> todos) {
    public ScheduleDto toDto(){
        List<TodoDto> todoDtos = todos.stream().map(TodoRequest::toDto).toList();

        return ScheduleDto.builder().id(id).todos(todoDtos).build();
    }
}
