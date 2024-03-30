package com.simplenotes.model.service;

import com.simplenotes.model.dto.registration.RegistrationFormDto;
import com.simplenotes.model.entity.UserEntity;
import com.simplenotes.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final UserMapper userMapper;

    public void registerUser(RegistrationFormDto registrationFormDto) {
        UserEntity userEntity = userMapper.registrationFormToEntity(registrationFormDto);
        userService.saveEntity(userEntity);
    }

}
