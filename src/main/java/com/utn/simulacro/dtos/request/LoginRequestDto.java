package com.utn.simulacro.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(

        @NotBlank
        String username,

        @NotBlank
        @Size(min = 8, max = 12)
        String password
) {
}
