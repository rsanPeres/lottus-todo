package com.lottus.todo.core.controller.request;

import com.lottus.todo.core.security.dto.UserDto;

public record SigninRequest(String email, String password) {
    public UserDto toDto(){
        return new UserDto(null, null, null, email, password, null, null);
    }
}
