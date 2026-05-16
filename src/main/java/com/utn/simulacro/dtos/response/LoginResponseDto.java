package com.utn.simulacro.dtos.response;

public record LoginResponseDto(
        String username,
        String role,
        String message
) {
}
