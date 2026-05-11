package com.utn.simulacro.repositories;

import com.utn.simulacro.enums.ReservationStatus;
import com.utn.simulacro.models.Member;
import com.utn.simulacro.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    List<Reservation> findByStatus (ReservationStatus status);

    List<Reservation> findByMember (Member member);

    List<Reservation> findByMember_Id (Long memberId);

}
