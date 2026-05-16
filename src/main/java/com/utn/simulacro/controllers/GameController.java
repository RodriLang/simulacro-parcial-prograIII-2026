package com.utn.simulacro.controllers;

import com.utn.simulacro.dtos.request.GameRequestDto;
import com.utn.simulacro.dtos.response.GameResponseDto;
import com.utn.simulacro.services.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;


    @PostMapping
    private ResponseEntity<GameResponseDto> create(@RequestBody @Valid GameRequestDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.create(request));
    }

    @GetMapping
    private ResponseEntity<List<GameResponseDto>> getAll(@RequestParam(required = false) Boolean active){
        return ResponseEntity.ok(gameService.getAll(active));
    }

    @GetMapping("/{id}")
    private ResponseEntity<GameResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(gameService.getById(id));
    }
}

