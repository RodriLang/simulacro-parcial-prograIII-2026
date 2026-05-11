package com.utn.simulacro.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class GameRequestDto {

    @NotBlank(message = "Se debe ingresar un nombre")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @NotBlank(message = "Se debe ingresar una categoria")
    @Size(max = 100, message = "La categoria debe tener menos de 100 caracteres")
    private String category;

    @Positive(message = "El numero debe ser mayor a cero")
    private Integer minAge;

    @Positive(message = "El stock no puede ser negativo")
    private Integer availableStock;
}
