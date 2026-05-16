package com.utn.simulacro.services.impl;
import com.utn.simulacro.dtos.request.LoginRequestDto;
import com.utn.simulacro.dtos.response.LoginResponseDto;
import com.utn.simulacro.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    public LoginResponseDto login(LoginRequestDto request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return new LoginResponseDto(
                userDetails.getUsername(),
                userDetails.getRole(),
                "Login exitoso"
        );
    }
}