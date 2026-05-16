package com.utn.simulacro.dtos.response;

import com.utn.simulacro.enums.RoleType;

public record UserResponseDto(

        Long id,

        String username,

        RoleType role
) {
}
