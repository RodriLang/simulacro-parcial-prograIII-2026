package com.utn.simulacro.services;

import com.utn.simulacro.dtos.request.ReservationCancelRequestDto;
import com.utn.simulacro.dtos.request.ReservationRequestDto;
import com.utn.simulacro.dtos.response.ReservationResponseDto;
import com.utn.simulacro.enums.ReservationStatus;

import java.util.List;

public interface ReservationService {

    ReservationResponseDto create(ReservationRequestDto request);

    List<ReservationResponseDto> getAllByStatus(ReservationStatus status);

    List<ReservationResponseDto> getByMember(Long memberId);

    ReservationResponseDto cancel(Long reservationId, ReservationCancelRequestDto request);
}
