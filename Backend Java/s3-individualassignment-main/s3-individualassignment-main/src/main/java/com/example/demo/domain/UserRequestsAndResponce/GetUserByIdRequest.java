package com.example.demo.domain.UserRequestsAndResponce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetUserByIdRequest {
    private Long id;
}
