package com.utn.simulacro.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReservationCancelRequestDto {

    @NotNull(message = "La fecha de cancelacion es requerida")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate cancellationDate;
}
