package com.utn.simulacro.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberRequestDto {

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @NotBlank(message = "El email es requerido")
    @Email(message = "Formato de email incorrecto")
    private String email;

    @NotBlank(message = "El DNI es requerido")
    @Size(min = 7, max = 20, message = "El DNI debe tener entre 7 y 20 caracteres")
    private String dni;

    @NotNull(message = "La fecha de nacimiento es requerida")
    @Past(message = "La fecha de nacimiento no es valida")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

}
