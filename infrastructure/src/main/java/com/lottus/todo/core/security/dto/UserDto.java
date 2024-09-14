package com.lottus.todo.core.security.dto;

import com.lottus.todo.core.security.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private RoleEnum role;

    private String token;
}
