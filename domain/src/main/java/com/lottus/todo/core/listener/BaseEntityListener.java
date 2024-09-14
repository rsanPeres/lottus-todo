package com.lottus.todo.core.listener;

import com.lottus.todo.core.domain.BaseEntity;
import com.lottus.todo.core.domain.UserEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;

public class BaseEntityListener {
    @PrePersist
    public void prePersist(BaseEntity entity) {
        entity.registrationDate(LocalDate.now());
        entity.updateAt(LocalDate.now());
        entity.active(true);

        entity.user(getLoggedUser());
    }

    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        entity.updateAt(LocalDate.now());
    }

    public static UserEntity getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserEntity) {
            return (UserEntity) authentication.getPrincipal();
        }
        return null;
    }
}
