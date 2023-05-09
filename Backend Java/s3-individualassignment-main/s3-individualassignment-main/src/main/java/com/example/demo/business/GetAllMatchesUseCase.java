package com.example.demo.business;

import com.example.demo.domain.MatchesRequestsAndResponses.GetAllMatchResponse;

public interface GetAllMatchesUseCase {
    GetAllMatchResponse findAllMatches();
}
