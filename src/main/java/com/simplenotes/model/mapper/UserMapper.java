package com.simplenotes.model.mapper;

import com.simplenotes.model.dto.UserDto;
import com.simplenotes.model.dto.registration.RegistrationFormDto;
import com.simplenotes.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserDto userEntityToDto(UserEntity user) {
        return UserDto.builder()
                .id(user.getId())
                .userIcon(user.getUserIcon())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public UserEntity registrationFormToEntity(RegistrationFormDto formDto) {
        return UserEntity.builder()
                .username(formDto.getUsername())
                .firstName(formDto.getFirstName())
                .lastName(formDto.getLastName())
                .email(formDto.getEmail())
                .phoneNumber(formDto.getPhoneNumber())
                .password(passwordEncoder.encode(formDto.getPassword()))
                .build();
    }
}
