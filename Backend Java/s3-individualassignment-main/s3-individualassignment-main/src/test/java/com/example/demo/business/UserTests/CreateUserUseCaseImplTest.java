package com.example.demo.business.UserTests;

import com.example.demo.business.impl.User.CreateUserUseCaseImpl;
import com.example.demo.domain.UserRequestsAndResponce.CreateUserRequest;
import com.example.demo.domain.UserRequestsAndResponce.CreateUserResponse;
import com.example.demo.repository.Role;
import com.example.demo.repository.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseImplTest {
@Mock
private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Test
    void save_User_shouldReturn_200() {
        String encodedPassword = passwordEncoder.encode("asdasd");
        UserEntity expectedUser = UserEntity.builder()
                .name("Ivan")
                .email("ivan40@gmail.com")
                .username("Ivan40")
                .password(encodedPassword)
               .build();

        expectedUser.setUserRoles((Set.of(
                UserRoleEntity.builder()
                        .user(expectedUser)
                        .role(Role.Customer)
                        .build())));

        UserEntity savedUser = UserEntity.builder()
                .id(1L)
                .name("Ivan")
                .email("ivan40@gmail.com")
                .username("Ivan40")
                .password("asdasd").build();


        when(userRepositoryMock.save(expectedUser)).thenReturn(savedUser);

        CreateUserRequest request = CreateUserRequest.builder()
                .name("Ivan")
                .email("ivan40@gmail.com")
                .username("Ivan40")
                .password("asdasd")
               .build();

        CreateUserResponse actualResponse = createUserUseCase.createUser(request);

        CreateUserResponse expectedResponse = CreateUserResponse.builder()
                .id(1L)
                .build();

        assertEquals(expectedResponse,actualResponse);

        verify(userRepositoryMock).save(expectedUser);
    }
}
