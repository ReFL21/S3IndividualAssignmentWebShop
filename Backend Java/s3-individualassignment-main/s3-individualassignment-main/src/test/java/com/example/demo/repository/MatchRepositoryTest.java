package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MatchRepositoryTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private MatchRepository matchRepository;

    @Test
    void save_shouldSaveMatchWithFields(){
        FootballMatchEntity match = FootballMatchEntity.builder()
                .id(1L)
                .date("15-05-2023")
                .firstTeam("MyTeam")
                .secondTeam("YourTeam")
                .build();

        FootballMatchEntity savedMatch = matchRepository.save(match);
        assertNotNull(savedMatch.getId());
        savedMatch = entityManager.find(FootballMatchEntity.class, savedMatch.getId());
        FootballMatchEntity expectedMatch = FootballMatchEntity.builder()
                .id(1L)
                .firstTeam("MyTeam")
                .secondTeam("YourTeam")
                .date("15-05-2023")
                .build();

        assertEquals(expectedMatch, savedMatch);
    }

    @Test
    void save_AddMatchWithoutAnyFields_throwsExeption(){
        FootballMatchEntity match = FootballMatchEntity.builder().build();
        assertThrows(ConstraintViolationException.class, () -> matchRepository.save(match));
    }

}
