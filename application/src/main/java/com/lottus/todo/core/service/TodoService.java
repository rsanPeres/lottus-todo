package com.lottus.todo.core.service;

import com.lottus.todo.core.dto.TodoDto;
import com.lottus.todo.core.enums.PriorityEnum;
import com.lottus.todo.core.enums.StatusEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TodoService {
    TodoDto create(TodoDto source);
    TodoDto update(TodoDto source);
    TodoDto getById(UUID id);
    List<StatusEnum> getAllPossibleStatusForTodo();
    List<PriorityEnum> getAllPossiblePriorityForTodo();
    List<TodoDto> getAll(Integer page, Integer size, String title, LocalDate dueDate, LocalDate executionDate,
                         PriorityEnum priority, StatusEnum status, Boolean highlight);
    void delete(UUID id);
}
