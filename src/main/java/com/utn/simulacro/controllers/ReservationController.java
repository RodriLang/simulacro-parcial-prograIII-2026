package com.utn.simulacro.controllers;

import com.utn.simulacro.dtos.request.ReservationCancelRequestDto;
import com.utn.simulacro.dtos.request.ReservationRequestDto;
import com.utn.simulacro.dtos.response.ReservationResponseDto;
import com.utn.simulacro.enums.ReservationStatus;
import com.utn.simulacro.services.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping()
    private ResponseEntity<ReservationResponseDto> create(
            @RequestBody @Valid ReservationRequestDto request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.create(request));
    }

    @GetMapping
    private ResponseEntity<List<ReservationResponseDto>> getAll(
            @RequestParam(required = false) ReservationStatus status
    ) {
        return ResponseEntity.ok(reservationService.getAllByStatus(status));
    }

    @GetMapping("/member/{memberId}")
    private ResponseEntity<List<ReservationResponseDto>> getByMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(reservationService.getByMember(memberId));
    }

    @PatchMapping("/{reservationId}/cancel")
    private ResponseEntity<ReservationResponseDto> cancel (
            @PathVariable Long reservationId,
            @RequestBody ReservationCancelRequestDto request
    ){
        return ResponseEntity.ok(reservationService.cancel(reservationId, request));
    }

}
