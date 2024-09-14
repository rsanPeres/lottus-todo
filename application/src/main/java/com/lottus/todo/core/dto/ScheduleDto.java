package com.lottus.todo.core.dto;

import com.lottus.todo.core.domain.ScheduleEntity;
import com.lottus.todo.core.domain.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {
    private UUID id;

    private Collection<TodoDto> todos = new ArrayList<>();

    public ScheduleDto(ScheduleEntity entity){
        id = entity.getId();
        todos = entity.getTodos().stream().map(TodoDto::new).toList();
    }

    public ScheduleEntity toEntity(){
        List<TodoEntity> todoEntities = todos.stream().map(TodoDto::toEntity).toList();

        return ScheduleEntity.builder().id(id).todos(todoEntities).build();
    }
}
