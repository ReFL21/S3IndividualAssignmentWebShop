package com.example.demo.business;

import com.example.demo.domain.UserRequestsAndResponce.LoginRequest;
import com.example.demo.domain.UserRequestsAndResponce.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
