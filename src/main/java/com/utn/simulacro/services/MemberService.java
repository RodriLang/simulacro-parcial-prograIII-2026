package com.utn.simulacro.services;

import com.utn.simulacro.dtos.request.MemberRequestDto;
import com.utn.simulacro.dtos.response.MemberResponseDto;
import com.utn.simulacro.models.Member;

import java.util.List;

public interface MemberService {

    MemberResponseDto create(MemberRequestDto request);

    List<MemberResponseDto> getAll();

    MemberResponseDto getById(Long id);

    Member getEntityById(Long id);
}
