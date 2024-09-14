package com.lottus.todo.core.controller.request;

import com.lottus.todo.core.security.dto.UserDto;

public record SignUpRequest(String firstName, String lastName, String email, String password) {
    public UserDto toDto(){
        return new UserDto(null, firstName, lastName, email, password, null, null);
    }
}
