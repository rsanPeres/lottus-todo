package com.lottus.todo.core.controller;

import com.lottus.todo.core.controller.request.TodoRequest;
import com.lottus.todo.core.controller.response.TodoResponse;
import com.lottus.todo.core.dto.TodoDto;
import com.lottus.todo.core.enums.PriorityEnum;
import com.lottus.todo.core.enums.StatusEnum;
import com.lottus.todo.core.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Api(tags = "To do")
@RequestMapping("api/v1/todo")
public class TodoController {
    private final TodoService todoService;

    @PatchMapping
    @ApiOperation(value = "Atualizar to do")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "Header")})
    public ResponseEntity<TodoResponse> updateTodo(@Valid @RequestBody TodoRequest request) {
        TodoDto dto = todoService.update(request.toDto());

        return ResponseEntity.status(HttpStatus.OK).body(new TodoResponse(dto));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar um to do pelo ID")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "Header")})
    public ResponseEntity<TodoResponse> getTodoById(@PathVariable UUID id) {
        TodoDto dto = todoService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new TodoResponse(dto));
    }

    @GetMapping("/status")
    @ApiOperation(value = "Buscar todos os status possiveis para to do")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "Header")})
    public ResponseEntity<List<StatusEnum>> getAllStatusPossibleFotTodo() {
        List<StatusEnum> status = todoService.getAllPossibleStatusForTodo();

        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @GetMapping("/priority")
    @ApiOperation(value = "Buscar todos os status possiveis para to do")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "Header")})
    public ResponseEntity<List<PriorityEnum>> getAllPrioritiesPossibleFotTodo() {
        List<PriorityEnum> status = todoService.getAllPossiblePriorityForTodo();

        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @GetMapping
    @ApiOperation(value = "Buscar todo to dos do usuário")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "Header")})
    public ResponseEntity<List<TodoResponse>> getAllTodo(@RequestParam(value = "page", required = false) Integer page,
                                                         @RequestParam(value = "size", required = false) Integer size,
                                                         @RequestParam(required = false) String title,
                                                         @RequestParam(required = false) LocalDate dueDate,
                                                         @RequestParam(required = false) LocalDate executionDate,
                                                         @RequestParam(required = false) PriorityEnum priority,
                                                         @RequestParam(required = false) StatusEnum status,
                                                         @RequestParam(required = false) Boolean highlight) {
        List<TodoDto> dtos = todoService.getAll(page, size, title, dueDate, executionDate, priority, status, highlight);

        List<TodoResponse> responses = dtos.stream().map(TodoResponse::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @ApiOperation(value = "Desativar To do pelo ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "Header")})
    public ResponseEntity<String> deleteTodo(@PathVariable UUID id) {
        todoService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
