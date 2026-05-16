package com.utn.simulacro.services.impl;

import com.utn.simulacro.dtos.request.UserRequestDto;
import com.utn.simulacro.dtos.response.UserResponseDto;
import com.utn.simulacro.enums.RoleType;
import com.utn.simulacro.mappers.UserMapper;
import com.utn.simulacro.models.User;
import com.utn.simulacro.repositories.UserRepository;
import com.utn.simulacro.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(UserRequestDto request) {

        User user = userMapper.toEntity(request);
        // Seteamos la contraseña encriptada, nunca en texto plano
        user.setPassword(passwordEncoder.encode(request.password()));

        if(request.role() == null){
            user.setRole(RoleType.USER);
        }

        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }
}
