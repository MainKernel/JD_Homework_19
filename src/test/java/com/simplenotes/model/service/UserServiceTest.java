package com.simplenotes.model.service;

import com.simplenotes.anotation.IT;
import com.simplenotes.database.IntegrationTestDatabase;
import com.simplenotes.model.entity.UserEntity;
import com.simplenotes.model.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;


@IT
public class UserServiceTest extends IntegrationTestDatabase {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository repository;


    @Test
    public void findUserByUsernameTest() {
        UserEntity johnySilver = userService.findUserByUsername("JohnySilver");
        Long expected = 1L;
        Long actual = johnySilver.getId();

        assertEquals(expected, actual);
    }

    @Test()
    public void findUserByUsernameExceptionTest() {
        assertThrowsExactly(UsernameNotFoundException.class, () -> userService.findUserByUsername("Flash"));
    }

    @Test
    public void saveUserTest() {
        int expected = repository.findAll().size() + 1;
        UserEntity userEntity = userService.saveEntity(UserEntity.builder()
                .userIcon("no icon")
                .username("userName")
                .email("email")
                .firstName("firstName")
                .lastName("lastName")
                .password("password")
                .phoneNumber("42342342")
                .build());
        int actual = repository.findAll().size();

        assertNotNull(userEntity);
        assertEquals(expected, actual);
    }


}