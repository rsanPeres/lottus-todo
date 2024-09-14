package com.lottus.todo.core.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SCHEDULE")
public class ScheduleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCHEDULE_ID")
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SCHEDULE_TODO",
            joinColumns = @JoinColumn(name = "SCHEDULE_ID"),
            inverseJoinColumns = @JoinColumn(name = "TODO_ID"))
    private Collection<TodoEntity> todos = new ArrayList<>();
}
