package com.example.demo.business;

import com.example.demo.domain.User;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> getUser(long id);
}
