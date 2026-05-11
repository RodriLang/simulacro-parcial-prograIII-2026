package com.utn.simulacro.services.impl;

import com.utn.simulacro.dtos.request.GameRequestDto;
import com.utn.simulacro.dtos.response.GameResponseDto;
import com.utn.simulacro.exceptions.EntityNotFoundException;
import com.utn.simulacro.mappers.GameMapper;
import com.utn.simulacro.models.Game;
import com.utn.simulacro.repositories.GameRepository;
import com.utn.simulacro.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    @Override
    public GameResponseDto create(GameRequestDto request) {

        Game newGame = gameMapper.toEntity(request);

        Game savedGame = gameRepository.save(newGame);

        return gameMapper.toDto(savedGame);
    }

    @Override
    public List<GameResponseDto> getAll(Boolean active) {
        List<Game> games;

        if (Objects.isNull(active)) {
            games = gameRepository.findAll();
        } else {
            games = gameRepository.findByActive(active);
        }

        return games.stream()
                .map(gameMapper::toDto)
                .toList();
    }

    @Override
    public GameResponseDto getById(Long gameId) {
        Game game = getEntityById(gameId);
        return gameMapper.toDto(game);
    }

    @Override
    public Game getEntityById(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Juego no encontrado con el ID = " + gameId));
    }

    @Override
    public void updateStock(Long gameId, Integer stock) {
        Game game = getEntityById(gameId);

        game.setAvailableStock(stock);

        gameRepository.save(game);
    }
}
