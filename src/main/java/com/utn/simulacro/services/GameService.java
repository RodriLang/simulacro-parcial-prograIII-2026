package com.utn.simulacro.services;

import com.utn.simulacro.dtos.request.GameRequestDto;
import com.utn.simulacro.dtos.response.GameResponseDto;
import com.utn.simulacro.models.Game;

import java.util.List;

public interface GameService {

    GameResponseDto create(GameRequestDto request);

    List<GameResponseDto> getAll(Boolean active);

    GameResponseDto getById(Long gameId);

    Game getEntityById(Long gameId);

    void updateStock(Long gameId, Integer stock);
}
