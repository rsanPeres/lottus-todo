package com.lottus.todo.core.controller;

import com.lottus.todo.core.controller.request.SignUpRequest;
import com.lottus.todo.core.controller.request.SigninRequest;
import com.lottus.todo.core.controller.response.JwtAuthenticationResponse;
import com.lottus.todo.core.security.dto.UserDto;
import com.lottus.todo.core.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @ApiOperation(value = "Cadastro de usuário")
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        UserDto dto = authenticationService.signup(request.toDto());

        return ResponseEntity.status(HttpStatus.OK).body(new JwtAuthenticationResponse(dto));
    }

    @ApiOperation(value = "Autenticação de usuário")
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@Valid @RequestBody SigninRequest request) {
        UserDto dto = authenticationService.signin(request.toDto());

        return ResponseEntity.status(HttpStatus.OK).body(new JwtAuthenticationResponse(dto));
    }
}