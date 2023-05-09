package com.example.demo.domain.MatchesRequestsAndResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMatchRequest {

    @NotBlank
    private String firstTeam;

    @NotBlank
    private String secondTeam;

    @NotBlank
    private String date;

}
