package com.example.demo.domain.MatchesRequestsAndResponses;

import com.example.demo.domain.FootballMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GetMatchResponse {
    List<FootballMatch> mathes;
}
