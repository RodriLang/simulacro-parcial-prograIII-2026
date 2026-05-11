package com.utn.simulacro.mappers;

import com.utn.simulacro.dtos.request.GameRequestDto;
import com.utn.simulacro.dtos.response.GameResponseDto;
import com.utn.simulacro.models.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {

    Game toEntity (GameRequestDto dto);

    GameResponseDto toDto(Game entity);
}
