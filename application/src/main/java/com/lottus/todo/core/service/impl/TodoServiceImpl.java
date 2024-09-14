package com.lottus.todo.core.service.impl;

import com.lottus.todo.core.domain.TodoEntity;
import com.lottus.todo.core.dto.TodoDto;
import com.lottus.todo.core.enums.PriorityEnum;
import com.lottus.todo.core.enums.StatusEnum;
import com.lottus.todo.core.listener.BaseEntityListener;
import com.lottus.todo.core.repository.TodoRepository;
import com.lottus.todo.core.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    @Override
    public TodoDto create(TodoDto source) {
        TodoEntity saved = todoRepository.save(source.toEntity());

        return new TodoDto(saved);
    }

    @Override
    public TodoDto update(TodoDto source) {
        TodoEntity entity = getActiveById(source.getId());

        if (Objects.nonNull(source.getTitle())) entity.setTitle(source.getTitle());
        if (Objects.nonNull(source.getDueDate())) entity.setDueDate(source.getDueDate());
        if (Objects.nonNull(source.getExecutionDate())) entity.setExecutionDate(source.getExecutionDate());
        if (Objects.nonNull(source.getPriority())) entity.setPriority(source.getPriority());
        if (Objects.nonNull(source.getHighlight())) entity.setHighlight(source.getHighlight());
        if (Objects.nonNull(source.getStatus())) {
            entity.setStatus(source.getStatus());
            if (source.getStatus() == StatusEnum.FINALIZADO) entity.setHighlight(false);
        }

        TodoEntity saved = todoRepository.save(entity);
        return new TodoDto(saved);
    }

    @Override
    public TodoDto getById(UUID id) {
        TodoEntity entity = getActiveById(id);

        return new TodoDto(entity);
    }

    @Override
    public List<StatusEnum> getAllPossibleStatusForTodo(){
        return Arrays.stream(StatusEnum.values()).toList();
    }

    @Override
    public List<PriorityEnum> getAllPossiblePriorityForTodo(){
        return Arrays.stream(PriorityEnum.values()).toList();
    }

    @Override
    public List<TodoDto> getAll(Integer page, Integer size, String title, LocalDate dueDate, LocalDate executionDate,
                                PriorityEnum priority, StatusEnum status, Boolean highlight) {
        UUID userId = Objects.requireNonNull(BaseEntityListener.getLoggedUser()).getId();

        Pageable pageable = page == null || size == null ? Pageable.unpaged() : PageRequest.of(page, size);

        List<TodoEntity> entities = todoRepository.findAllActiveByFilters(pageable, title, dueDate, executionDate,
                priority, status, highlight, userId);

        List<TodoDto> dtos = entities.stream().map(TodoDto::new).toList();
        return dtos;
    }

    @Override
    public void delete(UUID id) {
        TodoEntity entity = getActiveById(id);

        entity.active(false);

        todoRepository.save(entity);
    }

    private TodoEntity getActiveById(UUID id){
        UUID userId = Objects.requireNonNull(BaseEntityListener.getLoggedUser()).getId();

        return todoRepository.findById(id, userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "To do não encontrado pelo id: " + id));
    }
}
