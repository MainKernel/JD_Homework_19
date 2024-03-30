package com.simplenotes.model.service;

import com.simplenotes.model.entity.UserEntity;
import com.simplenotes.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public UserEntity findUserByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserEntity saveEntity(UserEntity user) {
        return repository.save(user);
    }
}
