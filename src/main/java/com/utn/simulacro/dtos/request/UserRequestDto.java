package com.utn.simulacro.dtos.request;

import com.utn.simulacro.enums.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(

        @NotBlank
        String username,

        @NotBlank
        @Size(min = 8, max = 12)
        String password,

        RoleType role
) {
}
