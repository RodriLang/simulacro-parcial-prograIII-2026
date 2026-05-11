package com.utn.simulacro.dtos.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MemberResponseDto {

    private Long id;

    private String name;

    private String email;

    private String dni;

    private Integer age;

    private LocalDate registrationDate;

    private List<ReservationResponseDto> reservations;
}
