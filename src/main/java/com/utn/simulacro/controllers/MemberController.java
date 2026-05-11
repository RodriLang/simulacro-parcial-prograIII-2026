package com.utn.simulacro.controllers;

import com.utn.simulacro.dtos.request.MemberRequestDto;
import com.utn.simulacro.dtos.response.MemberResponseDto;
import com.utn.simulacro.services.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    private ResponseEntity<MemberResponseDto> create(@RequestBody @Valid MemberRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.create(request));
    }

    @GetMapping
    private ResponseEntity<List<MemberResponseDto>> getAll() {
        return ResponseEntity.ok(memberService.getAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<MemberResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getById(id));
    }
}