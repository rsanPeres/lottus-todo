package com.lottus.todo.core.controller;

import com.lottus.todo.core.controller.request.ScheduleRequest;
import com.lottus.todo.core.controller.response.ScheduleResponse;
import com.lottus.todo.core.dto.ScheduleDto;
import com.lottus.todo.core.enums.PriorityEnum;
import com.lottus.todo.core.enums.StatusEnum;
import com.lottus.todo.core.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Api(tags = "Agenda")
@RequestMapping("api/v1/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    @ApiOperation(value = "Inserir nova agenda de to dos")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "Header")})
    public ResponseEntity<ScheduleResponse> createSchedule(@Valid @RequestBody ScheduleRequest request) {
        ScheduleDto dto = scheduleService.create(request.toDto());

        return ResponseEntity.status(HttpStatus.OK).body(new ScheduleResponse(dto));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar uma agenda de to dos pelo ID")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "Header")})
    public ResponseEntity<ScheduleResponse> getScheduleById(@PathVariable UUID id) {
        ScheduleDto dto = scheduleService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ScheduleResponse(dto));
    }

    @GetMapping
    @ApiOperation(value = "Buscar todas as agendas de to dos")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "Header")})
    public ResponseEntity<List<ScheduleResponse>> getAllSchedule(@RequestParam(value = "page",required = false) Integer page,
                                                               @RequestParam(value = "size", required = false) Integer size,
                                                               @RequestParam(required = false) @Nullable String title,
                                                               @RequestParam(required = false) @Nullable LocalDate dueDate,
                                                               @RequestParam(required = false) @Nullable LocalDate executionDate,
                                                               @RequestParam(required = false) @Nullable PriorityEnum priority,
                                                               @RequestParam(required = false) @Nullable StatusEnum status,
                                                               @RequestParam(required = false) @Nullable Boolean highlight) {
        List<ScheduleDto> dtos = scheduleService.getAll(page, size, title, dueDate, executionDate, priority, status, highlight);

        List<ScheduleResponse> responses = dtos.stream().map(ScheduleResponse::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @ApiOperation(value = "Desativar agenda pelo ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "Header")})
    public ResponseEntity<String>deleteSchedule(@PathVariable UUID id){
        scheduleService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
