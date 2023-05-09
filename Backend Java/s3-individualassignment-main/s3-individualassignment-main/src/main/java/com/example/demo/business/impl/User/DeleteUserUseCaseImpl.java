package com.example.demo.business.impl.User;

import com.example.demo.business.DeleteUserUseCase;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;

    @Override
    public void deleteUser(Long id) {
    userRepository.deleteById(id);
    }
}
