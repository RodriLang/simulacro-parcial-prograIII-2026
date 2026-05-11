package com.utn.simulacro.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 20)
    private String dni;

    private LocalDate birthdate;

    private LocalDate registrationDate;

    private Boolean active;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Reservation> reservations = new ArrayList<>();

    // Esta anotacion de JPA ejecuta este metodo antes de guardar la entidad
    @PrePersist
    private void onCreate() {
        if (registrationDate == null) {
            registrationDate = LocalDate.now();
        }
        if (active == null) {
            active = true;
        }
    }
}
