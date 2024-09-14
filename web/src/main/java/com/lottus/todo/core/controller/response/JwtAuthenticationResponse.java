package com.lottus.todo.core.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lottus.todo.core.security.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String email;
    private String token;

    public JwtAuthenticationResponse(UserDto dto){
        email = dto.getEmail();
        token = dto.getToken();
    }
}
