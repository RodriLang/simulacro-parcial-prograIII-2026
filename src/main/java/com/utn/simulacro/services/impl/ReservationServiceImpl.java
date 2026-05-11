package com.utn.simulacro.services.impl;

import com.utn.simulacro.dtos.request.ReservationCancelRequestDto;
import com.utn.simulacro.dtos.request.ReservationRequestDto;
import com.utn.simulacro.dtos.response.ReservationResponseDto;
import com.utn.simulacro.enums.ReservationStatus;
import com.utn.simulacro.exceptions.*;
import com.utn.simulacro.mappers.ReservationMapper;
import com.utn.simulacro.models.Game;
import com.utn.simulacro.models.Member;
import com.utn.simulacro.models.Reservation;
import com.utn.simulacro.repositories.GameRepository;
import com.utn.simulacro.repositories.ReservationRepository;
import com.utn.simulacro.services.GameService;
import com.utn.simulacro.services.MemberService;
import com.utn.simulacro.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final GameRepository gameRepository;
    private final ReservationMapper reservationMapper;
    private final GameService gameService;
    private final MemberService memberService;

    @Override
    public ReservationResponseDto create(ReservationRequestDto request) {

        Member member = memberService.getEntityById(request.getMemberId());
        Game game = gameService.getEntityById(request.getGameId());

        validateReservation(request, member, game);

        Reservation newReservation = reservationMapper.toEntity(request);
        newReservation.setMember(member);
        newReservation.setGame(game);

        int newStock = game.getAvailableStock() - request.getQuantity();

        // Actualizar el stock e inyectar repository para guardar el cambio
        game.setAvailableStock(newStock);
        gameRepository.save(game);

        // Otra forma
        // Crear un metodo en GameService para actualizar el stock
        gameService.updateStock(game.getId(), newStock);

        Reservation savedReservation = reservationRepository.save(newReservation);

        return reservationMapper.toDto(savedReservation);
    }

    @Override
    public List<ReservationResponseDto> getAllByStatus(ReservationStatus status) {

        List<Reservation> reservations;

        if (status == null){
            reservations = reservationRepository.findAll();
        } else {
            reservations = reservationRepository.findByStatus(status);
        }

        return reservations.stream()
                .map(reservationMapper::toDto)
                .toList();
    }

    @Override
    public List<ReservationResponseDto> getByMember(Long memberId) {

        Member member = memberService.getEntityById(memberId);


        return reservationRepository.findByMember(member).stream()
                .map(reservationMapper::toDto)
                .toList();

        // Otra forma de hacerlo mas eficiente pero con menos informacion descriptiva del error
        /*
        return reservationRepository.findByMember_Id(memberId).stream()
                .map(reservationMapper::toDto)
                .toList();
        */
    }

    @Override
    public ReservationResponseDto cancel(Long reservationId, ReservationCancelRequestDto request) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró una reserva con el ID= " + reservationId));

        validateCancellation(reservation, request.getCancellationDate());

        Game game = reservation.getGame();
        game.setAvailableStock(game.getAvailableStock() + reservation.getQuantity());

        reservation.setStatus(ReservationStatus.CANCELLED);
        reservation.setCancellationDate(request.getCancellationDate());

        // para persistir la modificacion del stock inyectamos gameRepository
        gameRepository.save(game);

        Reservation savedReservation = reservationRepository.save(reservation);

        return reservationMapper.toDto(savedReservation);
    }

    private void validateCancellation(Reservation reservation, LocalDate cancellationDate) {

        if (ReservationStatus.CANCELLED.equals(reservation.getStatus())) {
            throw new InvalidReservationException("La reserva ya se encuentra cancelada");
        }

        if (cancellationDate.isBefore(reservation.getReservationDate())) {
            throw new InvalidReservationException("La fecha de cancelación no puede ser anterior a la de reserva");
        }
    }

    private void validateReservation(ReservationRequestDto request, Member member, Game game) {

        if (!member.getActive()) {
            throw new InactiveMemberException("El socio se encuentra inactivo");
        }

        if (!game.getActive()) {
            throw new InactiveGameException("El juego no se encuentra disponible");
        }

        if (request.getQuantity() > game.getAvailableStock()) {
            throw new InsufficientStockException("La cantidad solicitada es mayor a la disponible");
        }

        int memberAge = Period.between(member.getBirthdate(), LocalDate.now()).getYears();

        if (memberAge < game.getMinAge()) {
            throw new MinimumAgeException("El socio no tiene la edad minima requerida para el juego solicitado");
        }

    }
}
