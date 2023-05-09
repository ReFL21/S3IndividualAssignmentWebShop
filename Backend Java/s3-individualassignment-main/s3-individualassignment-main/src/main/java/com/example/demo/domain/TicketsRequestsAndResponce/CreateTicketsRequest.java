package com.example.demo.domain.TicketsRequestsAndResponce;

import com.example.demo.domain.FootballMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTicketsRequest {


    @NotBlank
    private String date;

    @NotBlank
    private String place;

    @NotNull
    private FootballMatch match;
    @NotNull
    private long price;
    @NotNull
    private long quantity;
}
