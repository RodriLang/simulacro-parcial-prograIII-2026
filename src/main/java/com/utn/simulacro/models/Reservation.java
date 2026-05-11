package com.utn.simulacro.models;

import com.utn.simulacro.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate reservationDate;

    private LocalDate cancellationDate;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    // Esta anotacion de JPA ejecuta este metodo antes de guardar la entidad
    @PrePersist
    private void onCreate(){
        if(reservationDate == null){
            reservationDate = LocalDate.now();
        }
        if(status == null){
            status = ReservationStatus.CONFIRMED;
        }
    }
}
