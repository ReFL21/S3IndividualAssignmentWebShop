package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class FootballMatch {
    private Long id;
    private String firstTeam;
    private String secondTeam;
    private String date;
}
