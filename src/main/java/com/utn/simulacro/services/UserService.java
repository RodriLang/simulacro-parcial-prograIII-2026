package com.utn.simulacro.services;

import com.utn.simulacro.dtos.request.UserRequestDto;
import com.utn.simulacro.dtos.response.UserResponseDto;


public interface UserService {

    UserResponseDto createUser(UserRequestDto request);
}
