package com.lottus.todo.core.service.impl;

import com.lottus.todo.core.domain.ScheduleEntity;
import com.lottus.todo.core.dto.ScheduleDto;
import com.lottus.todo.core.enums.PriorityEnum;
import com.lottus.todo.core.enums.StatusEnum;
import com.lottus.todo.core.listener.BaseEntityListener;
import com.lottus.todo.core.repository.ScheduleRepository;
import com.lottus.todo.core.service.ScheduleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleDto create(ScheduleDto source) {
        ScheduleEntity saved = scheduleRepository.save(source.toEntity());

        return new ScheduleDto(saved);
    }

    @Transactional
    @Override
    public ScheduleDto update(ScheduleDto source) {
        ScheduleEntity entity = getActiveById(source.getId());

        if (Objects.nonNull(source.getTodos()) && !source.getTodos().isEmpty()) entity.setTodos(source.toEntity().getTodos());

        ScheduleEntity saved = scheduleRepository.save(entity);

        return new ScheduleDto(saved);
    }

    @Override
    public ScheduleDto getById(UUID id) {
        ScheduleEntity entity = getActiveById(id);

        return new ScheduleDto(entity);
    }

    @Override
    public List<ScheduleDto> getAll(Integer page, Integer size, String title, LocalDate dueDate, LocalDate executionDate,
                                       PriorityEnum priority, StatusEnum status, Boolean highlight) {
        UUID userId = Objects.requireNonNull(BaseEntityListener.getLoggedUser()).getId();

        Pageable pageable = page == null || size == null ? Pageable.unpaged() : PageRequest.of(page, size);

        List<ScheduleEntity> entities = scheduleRepository.findAllActiveByFilters(pageable, title, dueDate, executionDate,
                priority, status, highlight, userId);

        List<ScheduleDto> dtos = entities.stream().map(ScheduleDto::new).toList();

        return dtos;
    }

    @Override
    public void delete(UUID id) {
        ScheduleEntity entity = getActiveById(id);

        entity.active(false);

        scheduleRepository.save(entity);
    }

    private ScheduleEntity getActiveById(UUID id){
        UUID userId = Objects.requireNonNull(BaseEntityListener.getLoggedUser()).getId();

        return scheduleRepository.findById(id, userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda não encontrada pelo id: " + id));
    }
}
