package com.utn.simulacro.mappers;

import com.utn.simulacro.dtos.request.MemberRequestDto;
import com.utn.simulacro.dtos.response.MemberResponseDto;
import com.utn.simulacro.models.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.Period;

@Mapper(componentModel = "spring", uses = ReservationMapper.class)
public interface MemberMapper {

    Member toEntity (MemberRequestDto dto);

    @Mapping(target = "age", source = "birthdate", qualifiedByName = "calculateAgeByBirthday")
    MemberResponseDto toDto(Member entity);

    @Named("calculateAgeByBirthday")
    default Integer calculateAgeByBirthday(LocalDate birthdate){
        return Period.between(birthdate, LocalDate.now()).getYears();
    }
}
