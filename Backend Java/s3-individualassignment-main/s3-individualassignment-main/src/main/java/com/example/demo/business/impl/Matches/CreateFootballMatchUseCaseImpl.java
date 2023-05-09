package com.example.demo.business.impl.Matches;

import com.example.demo.business.CreateFootballMatchUseCase;
import com.example.demo.domain.MatchesRequestsAndResponses.CreateMatchRequest;
import com.example.demo.domain.MatchesRequestsAndResponses.CreateMatchResponse;
import com.example.demo.repository.FootballMatchEntity;
import com.example.demo.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateFootballMatchUseCaseImpl implements CreateFootballMatchUseCase {


    private final MatchRepository matchRepository;

//    public CreateFootballMatchUseCaseImpl() {}


    @Override
    @Transactional
    public CreateMatchResponse createMatch(CreateMatchRequest matchRequest){
        /*if (this.matchRepository.existsById(matchRequest.getId())){
            throw new Exception();
        }*/
        FootballMatchEntity footballMatchEntity = saveMatch(matchRequest);
        return CreateMatchResponse.builder()
                .id(footballMatchEntity.getId())
                .build();
    }

    private FootballMatchEntity saveMatch(CreateMatchRequest matchRequest){
        FootballMatchEntity footballMatchEntity = FootballMatchEntity.builder()
                .firstTeam(matchRequest.getFirstTeam())
                .secondTeam(matchRequest.getSecondTeam())
                .date(matchRequest.getDate().toString())
                .build();
        return this.matchRepository.save(footballMatchEntity);
    }

}
