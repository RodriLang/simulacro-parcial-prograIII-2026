package com.utn.simulacro.mappers;

import com.utn.simulacro.dtos.request.ReservationRequestDto;
import com.utn.simulacro.dtos.response.ReservationResponseDto;
import com.utn.simulacro.models.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation toEntity (ReservationRequestDto dto);

    @Mapping(target = "memberName", source = "member.name")
    @Mapping(target = "gameName", source = "game.name")
    ReservationResponseDto toDto(Reservation entity);

}
