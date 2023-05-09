package com.example.demo.business.impl.User;

import com.example.demo.business.AccessTokenEncoder;
import com.example.demo.business.LoginUseCase;
import com.example.demo.business.exeptions.InvalidCredentialsExeptions;
import com.example.demo.domain.AccessToken;
import com.example.demo.domain.UserRequestsAndResponce.LoginRequest;
import com.example.demo.domain.UserRequestsAndResponce.LoginResponse;
import com.example.demo.repository.UserEntity;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;



    @Override
    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user= userRepository.findByUsername(loginRequest.getUsername());
        if (user == null){
            throw new InvalidCredentialsExeptions();
        }

      // String hashedpass=passwordEncoder.encode(loginRequest.getPassword());
        if(!matchPassword(loginRequest.getPassword(), user.getPassword())){
            throw  new InvalidCredentialsExeptions();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }


    private boolean matchPassword(String rawPassword, String encodedPassword){
        if(passwordEncoder.matches(rawPassword, encodedPassword)){
            return true;
        }
         return false;
    }

    private String generateAccessToken(UserEntity user){
        Long user_id = user.getId() ;
        List<String> roles = user.getUserRoles().stream()
                .map(userRole-> userRole.getRole().toString())
                .toList();



        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .user_id(user_id)
                        .role(roles)
                        .subject(user.getUsername())
                        .build());
    }

}
