package com.example.demo.business.UserTests;

import com.example.demo.business.impl.User.GetAllUsersUseCaseImpl;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRequestsAndResponce.GetAllUsersResponse;
import com.example.demo.repository.UserEntity;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUsersUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    GetAllUsersUseCaseImpl getAllUsersUseCase;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void getUsers_shouldReturnAllUsers(){
        String encodedPassword = passwordEncoder.encode("asdasd");

        UserEntity user1 = UserEntity.builder()
                .id(1L)
                .username("Ivan40")
                .email("Ivan40@abv.bg")
                .name("Ivan")
                .password(encodedPassword)
                .build();

//        user1.setUserRoles((Set.of(
//                UserRoleEntity.builder()
//                        .user(user1)
//                        .role(Role.Customer)
//                        .build())));



        UserEntity user2 = UserEntity.builder()
                .id(2L)
                .username("Goshko")
                .email("Go6ko40@abv.bg")
                .name("Gorge")
                .password(encodedPassword)
                .build();
//        user2.setUserRoles((Set.of(
//                UserRoleEntity.builder()
//                        .user(user2)
//                        .role(Role.Customer)
//                        .build())));

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        GetAllUsersResponse actualResult = getAllUsersUseCase.getAllUsers();

        User actualUser1 = User.builder()
                .id(1L)
                .username("Ivan40")
                .email("Ivan40@abv.bg")
                .name("Ivan")
                .password(encodedPassword)
                .build();

        User actualUser2 = User.builder()
                .id(2L)
                .username("Goshko")
                .email("Go6ko40@abv.bg")
                .name("Gorge")
                .password(encodedPassword)
                .build();

        GetAllUsersResponse expectedResult = GetAllUsersResponse.builder()
                .users(List.of(actualUser1, actualUser2))
                .build();

        assertEquals(expectedResult, actualResult);
        verify(userRepository).findAll();
    }
}
