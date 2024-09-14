package com.lottus.todo.core.service.impl;

import com.lottus.todo.core.domain.UserEntity;
import com.lottus.todo.core.repository.UserRepository;
import com.lottus.todo.core.security.dto.UserDto;
import com.lottus.todo.core.security.enums.RoleEnum;
import com.lottus.todo.core.security.service.JwtService;
import com.lottus.todo.core.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public UserDto signup(UserDto request) {
        var user = UserEntity.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(RoleEnum.USER).active(true).updateAt(LocalDate.now()).registrationDate(LocalDate.now()).build();

        userRepository.save(user);

        var jwt = jwtService.generateToken(user);

        return UserDto.builder().token(jwt).email(request.getEmail()).build();
    }

    @Override
    public UserDto signin(UserDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email ou senha invalido."));

        var jwt = jwtService.generateToken(user);

        return UserDto.builder().token(jwt).build();
    }
}