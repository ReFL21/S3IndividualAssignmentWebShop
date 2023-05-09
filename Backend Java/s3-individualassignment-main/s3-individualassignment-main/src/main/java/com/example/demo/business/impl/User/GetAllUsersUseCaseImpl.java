package com.example.demo.business.impl.User;

import com.example.demo.business.GetAllUsersUseCase;
import com.example.demo.business.Converters.UserConverter;
import com.example.demo.domain.UserRequestsAndResponce.GetAllUsersResponse;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@AllArgsConstructor
public class GetAllUsersUseCaseImpl implements GetAllUsersUseCase {
    private UserRepository userRepository;

    @Override
    @Transactional
    public GetAllUsersResponse getAllUsers(){
        List<User> users;
        users = userRepository.findAll()
                .stream()
                .map(UserConverter::convert)
                .toList();

        return GetAllUsersResponse.builder()
                .users(users)
                .build();


}

}