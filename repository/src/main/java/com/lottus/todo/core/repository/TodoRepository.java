package com.lottus.todo.core.repository;

import com.lottus.todo.core.domain.TodoEntity;
import com.lottus.todo.core.enums.PriorityEnum;
import com.lottus.todo.core.enums.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<TodoEntity, UUID> {
    @Query(value = "SELECT t FROM TodoEntity t WHERE t.id = :id AND t.active = true " +
            "AND (:userId IS NULL OR t.user.id = :userId)")
    Optional<TodoEntity> findById(@Param("id") UUID id, @Param("userId") UUID userId);

    @Query(value = "SELECT t FROM TodoEntity t WHERE t.active = true " +
            "AND (:title IS NULL OR UPPER(CAST(t.title AS string)) LIKE CONCAT('%', UPPER(CAST(:title AS string)), '%')) " +
            "AND (:dueDate IS NULL OR t.dueDate = :dueDate) " +
            "AND (:executionDate IS NULL OR t.executionDate = :executionDate) " +
            "AND (:priority IS NULL OR t.priority = :priority) " +
            "AND (:status IS NULL OR t.status = :status) " +
            "AND (:highlight IS NULL OR t.highlight = :highlight) " +
            "AND (:userId IS NULL OR t.user.id = :userId) " +
            "ORDER BY t.highlight DESC, " +
            "CASE WHEN t.priority = 'URGENTE' THEN 0 ELSE 1 END, " +
            "CASE WHEN t.status = 'FINALIZADO' THEN 1 ELSE 0 END, " +
            "t.dueDate ASC")
    List<TodoEntity> findAllActiveByFilters(Pageable pageable, @Param("title") String title, @Param("dueDate") LocalDate dueDate,
                                            @Param("executionDate") LocalDate executionDate, @Param("priority") PriorityEnum priority,
                                            @Param("status") StatusEnum status, @Param("highlight") Boolean highlight,
                                            @Param("userId") UUID userId);
}
