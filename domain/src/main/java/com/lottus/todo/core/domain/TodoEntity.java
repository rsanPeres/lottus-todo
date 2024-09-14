package com.lottus.todo.core.domain;

import com.lottus.todo.core.enums.PriorityEnum;
import com.lottus.todo.core.enums.StatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Entity
@Table(name = "TODO")
public class TodoEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_ID")
    private UUID id;

    @NotBlank(message = "Obrigatório inserir um titulo")
    @Size(max = 255, message = "O titulo podeter no máximo 255 caracteres")
    @Column(name = "TITLE", nullable = false)
    private String title;

    @Future(message = "Data de validade não pode estar no passado")
    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @FutureOrPresent(message = "Data de execução não pode estar no passado")
    @Column(name = "EXECUTION_DATE")
    private LocalDate executionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIORITY")
    private PriorityEnum priority;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private StatusEnum status;

    @NotNull(message = "Prioridade é obrigatório")
    @Column(name = "HIGHLIGHT")
    private Boolean highlight;
}
