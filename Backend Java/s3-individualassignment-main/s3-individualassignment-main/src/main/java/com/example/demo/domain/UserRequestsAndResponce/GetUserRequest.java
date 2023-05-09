package com.example.demo.domain.UserRequestsAndResponce;

import lombok.Builder;
import lombok.Data;


import javax.validation.constraints.NotBlank;


@Data
@Builder

public class GetUserRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
