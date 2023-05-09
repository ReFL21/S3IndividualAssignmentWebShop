package com.example.demo.business.ConvertersTests;

import com.example.demo.business.Converters.MatchConverter;
import com.example.demo.domain.FootballMatch;
import com.example.demo.repository.FootballMatchEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FootballMatchConverterTest {

    @Test
    void shouldConvertFMatchEntityToFMatch(){
        FootballMatchEntity footballMatchEntity = FootballMatchEntity.builder()
                .id(1L)
                .firstTeam("Arsenal")
                .secondTeam("West Ham")
                .date("26-12-2022")
                .build();

        FootballMatch actualMatch = MatchConverter.convert(footballMatchEntity);
        FootballMatch expectedMatch = FootballMatch.builder()
                .id(1L)
                .firstTeam("Arsenal")
                .secondTeam("West Ham")
                .date("26-12-2022")
                .build();

        assertEquals(expectedMatch, actualMatch);
    }


}
