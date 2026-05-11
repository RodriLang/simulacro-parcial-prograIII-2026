package com.utn.simulacro.services.impl;

import com.utn.simulacro.dtos.request.MemberRequestDto;
import com.utn.simulacro.dtos.response.MemberResponseDto;
import com.utn.simulacro.exceptions.EntityNotFoundException;
import com.utn.simulacro.mappers.MemberMapper;
import com.utn.simulacro.models.Member;
import com.utn.simulacro.repositories.MemberRepository;
import com.utn.simulacro.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public MemberResponseDto create(MemberRequestDto request) {

        Member newMember = memberMapper.toEntity(request);

        // Estos datos se pueden incializar aca o en el metodo onUpdate de la clase
        newMember.setRegistrationDate(LocalDate.now());
        newMember.setActive(true);

        Member savedMember = memberRepository.save(newMember);

        return memberMapper.toDto(savedMember);
    }

    @Override
    public List<MemberResponseDto> getAll() {
        return memberRepository.findAll().stream()
                .map(memberMapper::toDto)
                .toList();
    }

    @Override
    public MemberResponseDto getById(Long id) {

        return memberMapper.toDto(getEntityById(id));
    }

    @Override
    public Member getEntityById(Long id) {

        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Socio no encontrado con el ID= " + id));
    }


}
