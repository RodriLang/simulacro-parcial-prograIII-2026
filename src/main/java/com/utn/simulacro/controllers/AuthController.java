package com.utn.simulacro.controllers;

import com.utn.simulacro.dtos.request.LoginRequestDto;
import com.utn.simulacro.dtos.request.UserRequestDto;
import com.utn.simulacro.dtos.response.LoginResponseDto;
import com.utn.simulacro.dtos.response.UserResponseDto;
import com.utn.simulacro.services.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }
}