package com.example.demo.business.UserTests;

import com.example.demo.business.impl.User.DeleteUserUseCaseImpl;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    @Test
    void deleteUser_shouldDeleteUser(){
        deleteUserUseCase.deleteUser(1L);
        verify(userRepositoryMock).deleteById(1L);
    }

}
