package com.example.demo.business.FootballMatchTests;

import com.example.demo.business.impl.Matches.DeleteFootballMatchUseCaseImpl;
import com.example.demo.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteFootballMatchUseCaseImplTest {
@Mock
private MatchRepository matchRepository;

@InjectMocks
DeleteFootballMatchUseCaseImpl deleteFootballMatchUseCase;
@Test
void deleteFootballMatch_shouldDelete(){
   deleteFootballMatchUseCase.deleteFootballMatch(1L);
   verify(matchRepository).deleteById(1L);
}
}
