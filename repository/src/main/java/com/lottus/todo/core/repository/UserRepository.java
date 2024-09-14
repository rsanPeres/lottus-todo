package com.lottus.todo.core.repository;

import com.lottus.todo.core.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query(value = "select u from UserEntity u where u.email = :email and u.active = true")
    Optional<UserEntity> findByEmail(@Param("email") String email);
}
