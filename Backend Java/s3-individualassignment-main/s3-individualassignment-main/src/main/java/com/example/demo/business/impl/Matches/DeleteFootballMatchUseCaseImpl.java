package com.example.demo.business.impl.Matches;

import com.example.demo.business.DeleteFMatchUseCase;
import com.example.demo.repository.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class DeleteFootballMatchUseCaseImpl implements DeleteFMatchUseCase {
    private final MatchRepository footballMatchRepository;

    @Override
    @Transactional
    public void deleteFootballMatch(Long id){
        this.footballMatchRepository.deleteById(id);
    }

}
