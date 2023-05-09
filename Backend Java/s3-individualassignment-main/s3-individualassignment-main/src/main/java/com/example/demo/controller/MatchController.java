package com.example.demo.controller;

import com.example.demo.business.CreateFootballMatchUseCase;
import com.example.demo.business.DeleteFMatchUseCase;
import com.example.demo.business.GetAllMatchesUseCase;
import com.example.demo.configuration.security.Isauthenticated.IsAuthenticated;
import com.example.demo.domain.MatchesRequestsAndResponses.CreateMatchRequest;
import com.example.demo.domain.MatchesRequestsAndResponses.CreateMatchResponse;
import com.example.demo.domain.MatchesRequestsAndResponses.GetAllMatchResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@AllArgsConstructor
@RequestMapping("/matches")
public class MatchController {
    private final GetAllMatchesUseCase getAllMatchesUseCase;
    private final CreateFootballMatchUseCase footballMatchUseCase;
    private final DeleteFMatchUseCase fMatchUseCase;

    @IsAuthenticated
    @RolesAllowed({"Admin"})
    @GetMapping
    public ResponseEntity<GetAllMatchResponse> getAllMatches() {
        GetAllMatchResponse response = getAllMatchesUseCase.findAllMatches();
        return ResponseEntity.ok(response);
    }

    @IsAuthenticated
    @RolesAllowed({"Admin"})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        fMatchUseCase.deleteFootballMatch(id);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"Admin"})
    @PostMapping("/create")
    public ResponseEntity<CreateMatchResponse> createMatch(@RequestBody @Valid CreateMatchRequest matchRequest) {
        CreateMatchResponse response = footballMatchUseCase.createMatch(matchRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
