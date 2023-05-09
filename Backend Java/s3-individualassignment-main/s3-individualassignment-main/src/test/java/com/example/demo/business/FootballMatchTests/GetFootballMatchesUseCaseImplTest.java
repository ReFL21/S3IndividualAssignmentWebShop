package com.example.demo.business.FootballMatchTests;

import com.example.demo.business.impl.Matches.GetAllMatchesUseCaseImpl;
import com.example.demo.domain.FootballMatch;
import com.example.demo.domain.MatchesRequestsAndResponses.GetAllMatchResponse;
import com.example.demo.repository.FootballMatchEntity;
import com.example.demo.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetFootballMatchesUseCaseImplTest {
    @InjectMocks
    GetAllMatchesUseCaseImpl getAllMatchesUseCase;
    @Mock
    MatchRepository matchRepository;

    @Test
    void getAllMatches_shouldReturnList(){
        FootballMatchEntity match1 = FootballMatchEntity.builder()
                .id(1L)
                .firstTeam("Arsenal")
                .secondTeam("West Ham")
                .date("26-12-2022")
                .build();

        FootballMatchEntity match2 = FootballMatchEntity.builder()
                .id(2L)
                .firstTeam("Brighton")
                .secondTeam("Wolves")
                .date("12-05-2023")
                .build();

        when(matchRepository.findAll()).thenReturn(List.of(match1, match2));
        GetAllMatchResponse actualResponse = getAllMatchesUseCase.findAllMatches();


        FootballMatch DTOmatch1 = FootballMatch.builder()
                .id(1L)
                .firstTeam("Arsenal")
                .secondTeam("West Ham")
                .date("26-12-2022")
                .build();

        FootballMatch DTOmatch2 = FootballMatch.builder()
                .id(2L)
                .firstTeam("Brighton")
                .secondTeam("Wolves")
                .date("12-05-2023")
                .build();


        GetAllMatchResponse expectedResponse = GetAllMatchResponse.builder()
                .matches(List.of(DTOmatch1, DTOmatch2))
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(matchRepository).findAll();
    }


}
