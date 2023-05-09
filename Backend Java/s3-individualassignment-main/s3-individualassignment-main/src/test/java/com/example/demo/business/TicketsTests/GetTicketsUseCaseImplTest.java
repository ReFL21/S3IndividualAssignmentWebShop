package com.example.demo.business.TicketsTests;

import com.example.demo.business.impl.Tickets.GetAllTicketsUseCaseImpl;
import com.example.demo.domain.FootballMatch;
import com.example.demo.domain.Tickets;
import com.example.demo.domain.TicketsRequestsAndResponce.GetAllTicketsResponse;
import com.example.demo.repository.FootballMatchEntity;
import com.example.demo.repository.TicketEntity;
import com.example.demo.repository.TicketsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTicketsUseCaseImplTest {
    @Mock
    private TicketsRepository ticketsRepository;
    @InjectMocks
    GetAllTicketsUseCaseImpl getAllTicketsUseCase;

    @Test
    void getTickets_shouldReturnAllTickets(){
        FootballMatchEntity matchEntity = FootballMatchEntity.builder()
                .id(1L)
                .firstTeam("Arsenal")
                .secondTeam("West Ham")
                .date("26-12-2022")
                .build();

        TicketEntity ticketEntity1 = TicketEntity.builder()
                .id(1L)
                .date("12-12-2022")
                .quantity(50)
                .place("There")
                .price(50)
                .match(matchEntity)
                .build();


        TicketEntity ticketEntity2 = TicketEntity.builder()
                .id(2L)
                .date("12-12-2022")
                .quantity(70)
                .place("There")
                .price(90)
                .match(matchEntity)
                .build();

        when(ticketsRepository.findAll()).thenReturn(List.of(ticketEntity1, ticketEntity2));

        GetAllTicketsResponse actualResponse = getAllTicketsUseCase.getAllTickets();

        FootballMatch matchDTO = FootballMatch.builder()
                .id(1L)
                .firstTeam("Arsenal")
                .secondTeam("West Ham")
                .date("26-12-2022")
                .build();

        Tickets ticketDTO1 = Tickets.builder()
                .id(1L)
                .date("12-12-2022")
                .quantity(50)
                .place("There")
                .price(50)
                .match(matchDTO)
                .build();


        Tickets ticketDTO2 = Tickets.builder()
                .id(2L)
                .date("12-12-2022")
                .quantity(70)
                .place("There")
                .price(90)
                .match(matchDTO)
                .build();


        GetAllTicketsResponse expectedResponse = GetAllTicketsResponse.builder()
                .tickets(List.of(ticketDTO1, ticketDTO2))
                .build();

        assertEquals(expectedResponse,actualResponse);
        verify(ticketsRepository).findAll();
    }
}
