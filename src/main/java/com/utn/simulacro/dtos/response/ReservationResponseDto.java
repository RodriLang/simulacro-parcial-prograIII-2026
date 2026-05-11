package com.utn.simulacro.dtos.response;

import com.utn.simulacro.enums.ReservationStatus;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationResponseDto {

    private Long id;

    private LocalDate reservationDate;

    private LocalDate cancellationDate;

    private Integer quantity;

    private ReservationStatus status;

    private String memberName;

    private String gameName;

    //En lugar de devolver los datos del socio y del juego como String
    //Podriamos devolver sus DTO o crear uno para mostrar algunos datos

    //private MemberResponseDto member;
    //private GameResponseDto game;
}
