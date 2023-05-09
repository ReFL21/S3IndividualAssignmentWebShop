package com.example.demo.domain.TicketsRequestsAndResponce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetTicketsByPlaceRequest {
    @NotBlank
    private String place;

}
