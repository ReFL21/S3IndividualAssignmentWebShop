package com.example.demo.business.impl.Matches;

import com.example.demo.business.GetAllMatchesUseCase;
import com.example.demo.business.Converters.MatchConverter;
import com.example.demo.domain.FootballMatch;
import com.example.demo.domain.MatchesRequestsAndResponses.GetAllMatchResponse;
import com.example.demo.repository.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@AllArgsConstructor
public class GetAllMatchesUseCaseImpl implements GetAllMatchesUseCase {
public MatchRepository matchRepository;
    @Override
    @Transactional
public GetAllMatchResponse findAllMatches(){
    List<FootballMatch> matchEntities = matchRepository.findAll()
            .stream()
            .map(MatchConverter::convert)
            .toList();
    return GetAllMatchResponse.builder()
            .matches(matchEntities)
            .build();

}


}
