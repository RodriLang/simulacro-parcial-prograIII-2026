package com.utn.simulacro.services;

import com.utn.simulacro.dtos.request.LoginRequestDto;
import com.utn.simulacro.dtos.request.UserRequestDto;
import com.utn.simulacro.dtos.response.LoginResponseDto;
import com.utn.simulacro.dtos.response.UserResponseDto;

public interface AuthService {

    UserResponseDto register(UserRequestDto request);

    LoginResponseDto login(LoginRequestDto request);

}