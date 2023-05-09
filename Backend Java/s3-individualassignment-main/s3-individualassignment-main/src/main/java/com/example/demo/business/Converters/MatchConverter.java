package com.example.demo.business.Converters;

import com.example.demo.domain.FootballMatch;
import com.example.demo.repository.FootballMatchEntity;

public class MatchConverter {
    public MatchConverter(){}

    public static FootballMatch convert(FootballMatchEntity footballMatchEntity){
        return FootballMatch.builder()
                .id(footballMatchEntity.getId())
                .date(footballMatchEntity.getDate())
                .firstTeam(footballMatchEntity.getFirstTeam())
                .secondTeam(footballMatchEntity.getSecondTeam())
                .build();

    }


}
