package com.example.demo.business;

import com.example.demo.domain.MatchesRequestsAndResponses.CreateMatchRequest;
import com.example.demo.domain.MatchesRequestsAndResponses.CreateMatchResponse;
import org.springframework.stereotype.Service;

@Service
public interface CreateFootballMatchUseCase {
    CreateMatchResponse createMatch(CreateMatchRequest matchRequest);
}
