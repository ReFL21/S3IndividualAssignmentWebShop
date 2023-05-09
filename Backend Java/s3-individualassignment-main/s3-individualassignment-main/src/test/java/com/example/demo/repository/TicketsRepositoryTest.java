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
public class TicketsRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TicketsRepository ticketsRepository;

    @Test
    void save_AddTicketWithFields(){
        FootballMatchEntity match = FootballMatchEntity.builder()

                .firstTeam("MyTeam")
                .secondTeam("YourTeam")
                .date("15-05-2023")
                .build();
    entityManager.persist(match);
        TicketEntity ticket = TicketEntity.builder()
                .id(2L)
                .date("15-05-2023")
                .match(match)
                .place("Stadium1")
                .quantity(50)
                .build();

        TicketEntity savedTicket = ticketsRepository.save(ticket);
        assertNotNull(savedTicket.getId());
        savedTicket = entityManager.find(TicketEntity.class, savedTicket.getId());
        TicketEntity expectedTicket = TicketEntity.builder()
                .id(savedTicket.getId())
                .date("15-05-2023")
                .match(match)
                .place("Stadium1")
                .quantity(50)
                .build();

        assertEquals(expectedTicket, savedTicket);
    }

    @Test
    void save_AddTicketsWithoutFields_throwsExeption(){
        TicketEntity empty = TicketEntity.builder().build();
        assertThrows(ConstraintViolationException.class, () -> ticketsRepository.save(empty));
    }
}
