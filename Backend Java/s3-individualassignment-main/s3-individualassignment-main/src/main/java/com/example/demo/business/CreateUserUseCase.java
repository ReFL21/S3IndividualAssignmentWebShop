package com.example.demo.business;

import com.example.demo.domain.UserRequestsAndResponce.CreateUserRequest;
import com.example.demo.domain.UserRequestsAndResponce.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest userRequest);
}
