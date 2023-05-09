package com.example.demo.domain.MatchesRequestsAndResponses;

import com.example.demo.domain.FootballMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllMatchResponse {
   List<FootballMatch> matches;

}
