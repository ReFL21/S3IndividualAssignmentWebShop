package com.example.demo.business.FootballMatchTests;

import com.example.demo.business.impl.Matches.CreateFootballMatchUseCaseImpl;
import com.example.demo.domain.MatchesRequestsAndResponses.CreateMatchRequest;
import com.example.demo.domain.MatchesRequestsAndResponses.CreateMatchResponse;
import com.example.demo.repository.FootballMatchEntity;
import com.example.demo.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class  CreateFootballMatchUseCaseImplTest {
    @Mock
    private MatchRepository matchRepository;
    @InjectMocks
    CreateFootballMatchUseCaseImpl footballMatchUseCase;

    @Test
    void save_Match_shouldReturn_200(){
        FootballMatchEntity expectedMatch = FootballMatchEntity.builder()
                .firstTeam("Arsenal")
                .secondTeam("West Ham")
                .date("26-12-2022")
                .build();
        FootballMatchEntity savedMatch = FootballMatchEntity.builder()
                .id(1L)
                .firstTeam("Arsenal")
                .secondTeam("West Ham")
                .date("26-12-2022")
                .build();

        when(matchRepository.save(expectedMatch)).thenReturn(savedMatch);
        CreateMatchRequest request = CreateMatchRequest.builder()
                .firstTeam("Arsenal")
                .secondTeam("West Ham")
                .date("26-12-2022")
                .build();


        CreateMatchResponse actualResponse = footballMatchUseCase.createMatch(request);

        CreateMatchResponse expectedResponse = CreateMatchResponse.builder()
                .id(1L)
                .build();

        assertEquals(expectedResponse,actualResponse);
        verify(matchRepository).save(expectedMatch);
    }





}
