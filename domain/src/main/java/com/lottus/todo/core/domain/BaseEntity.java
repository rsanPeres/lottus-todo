package com.lottus.todo.core.domain;

import com.lottus.todo.core.listener.BaseEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@MappedSuperclass
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Accessors(fluent = true)
@EntityListeners(BaseEntityListener.class)
public abstract class BaseEntity {
    @Column(name = "REGISTRATION_DATE")
    private LocalDate registrationDate;

    @Column(name = "UPDATE_AT")
    private LocalDate updateAt;

    @Column(name = "ACTIVE")
    private Boolean active;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;
}
