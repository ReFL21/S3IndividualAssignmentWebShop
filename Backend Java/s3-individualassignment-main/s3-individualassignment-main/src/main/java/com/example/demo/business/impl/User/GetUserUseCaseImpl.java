package com.example.demo.business.impl.User;

import com.example.demo.business.GetUserUseCase;
import com.example.demo.business.Converters.UserConverter;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private UserRepository userRepository;


    @Override
    @Transactional
    public Optional<User> getUser(long id) {
        return userRepository.findById(id).map(UserConverter::convert);
    }

}
