package com.example.demo.business.UserTests;

import com.example.demo.business.impl.User.GetUserUseCaseImpl;
import com.example.demo.domain.User;
import com.example.demo.repository.UserEntity;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Test
    void shouldReturnUserIfFound() {
        UserEntity user = UserEntity.builder()
                .id(12L)
                .name("Ivan")
                .username("Ivan40")
                .email("asdasd@abv.bg")
                .build();
        when(userRepositoryMock.findById(12L)).thenReturn(Optional.of(user));
        Optional<User> userOptional = getUserUseCase.getUser(12);
        User actualUser = userOptional.orElseThrow();

        User expectedUser = User.builder()
                .id(12L)
                .name("Ivan")
                .username("Ivan40")
                .email("asdasd@abv.bg")
                .build();

        assertEquals(expectedUser, actualUser);

        verify(userRepositoryMock).findById(12L);
    }





}


