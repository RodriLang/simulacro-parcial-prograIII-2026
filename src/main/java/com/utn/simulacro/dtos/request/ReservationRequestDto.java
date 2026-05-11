package com.utn.simulacro.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ReservationRequestDto {

    @NotNull(message = "La cantidad es requerida")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer quantity;

    @NotNull(message = "El Id del socio es requerido")
    private Long memberId;

    @NotNull(message = "El Id del juego es requerido")
    private Long gameId;
}
