package com.lottus.todo.core.service;

import com.lottus.todo.core.security.dto.UserDto;

public interface AuthenticationService {
    UserDto signup(UserDto request);

    UserDto signin(UserDto request);
}
