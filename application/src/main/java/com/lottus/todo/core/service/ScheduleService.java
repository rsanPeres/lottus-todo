package com.lottus.todo.core.service;

import com.lottus.todo.core.dto.ScheduleDto;
import com.lottus.todo.core.enums.PriorityEnum;
import com.lottus.todo.core.enums.StatusEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ScheduleService {
    ScheduleDto create(ScheduleDto source);
    ScheduleDto update(ScheduleDto source);
    ScheduleDto getById(UUID id);
    List<ScheduleDto> getAll(Integer page, Integer size, String title, LocalDate dueDate, LocalDate executionDate,
                                PriorityEnum priority, StatusEnum status, Boolean highlight);
    void delete(UUID id);
}
