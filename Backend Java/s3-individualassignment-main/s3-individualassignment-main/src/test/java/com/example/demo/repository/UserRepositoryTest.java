package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void save_shouldAddUserWithFields(){
        UserEntity user = UserEntity.builder()
                .id(1L)
                .username("Ivan40")
                .email("ivan40@gmail.com")
                .name("Ivan")
                .password("asdasd")
                .build();

        UserEntity saveduser = userRepository.save(user);
        assertNotNull(saveduser.getId());
        saveduser = entityManager.find(UserEntity.class, saveduser.getId());
        UserEntity expecteduser = UserEntity.builder()
                .id(saveduser.getId())
                .username("Ivan40")
                .email("ivan40@gmail.com")
                .name("Ivan")
                .password("asdasd")
                .build();

        assertEquals(expecteduser, saveduser);

    }

    @Test
    void save_AddUserWithFields_throwExeptions(){
        UserEntity empty = UserEntity.builder().build();
        assertThrows(ConstraintViolationException.class, () -> userRepository.save(empty));
    }

    @Test
    void findUserByUsername_shouldReturnUser_withFields(){
        entityManager.persist(UserEntity.builder()
                        .name("Hristo")
                        .username("ReFL123")
                        .email("hristokolev01@abv.bg")
                        .password("asdasd")
                .build());

        UserEntity actualUser = userRepository.findByUsername("ReFL123");
        assertNotNull(actualUser.getId());

    UserEntity expectedUser = UserEntity.builder()
            .id(actualUser.getId())
            .name("Hristo")
            .username("ReFL123")
            .email("hristokolev01@abv.bg")
            .password("asdasd")
            .build();

    assertEquals(expectedUser, actualUser);
    }

    @Test
    void findByUsername_shouldReturnNull_notUserFound(){
    UserEntity empty = userRepository.findByUsername("Test");
assertNull(empty);
    }

}
