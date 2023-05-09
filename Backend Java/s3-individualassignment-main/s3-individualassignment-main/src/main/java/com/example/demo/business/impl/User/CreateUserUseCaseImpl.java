package com.example.demo.business.impl.User;

import com.example.demo.business.CreateUserUseCase;
import com.example.demo.domain.UserRequestsAndResponce.CreateUserRequest;
import com.example.demo.domain.UserRequestsAndResponce.CreateUserResponse;
import com.example.demo.repository.Role;
import com.example.demo.repository.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest userRequest) {

        UserEntity userEntity = saveNewUser(userRequest);
        return CreateUserResponse.builder()
                .id(userEntity.getId())
                .build();

    }

    private UserEntity saveNewUser(CreateUserRequest userRequest){
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        UserEntity user = UserEntity.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(encodedPassword)
                .build();


        user.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .role(Role.Customer)
                        .user(user)
                        .build()
        ));

        return userRepository.save(user);
    }



}
