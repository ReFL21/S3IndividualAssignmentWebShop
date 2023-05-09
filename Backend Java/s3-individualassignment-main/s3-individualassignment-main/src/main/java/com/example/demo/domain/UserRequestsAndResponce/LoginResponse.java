package com.example.demo.domain.UserRequestsAndResponce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
   private String accessToken;



}
