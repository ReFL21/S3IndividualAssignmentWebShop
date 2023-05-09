package com.example.demo.business.TicketsTests;

import com.example.demo.business.impl.Tickets.CreateTicketUseCaseImpl;
import com.example.demo.domain.FootballMatch;
import com.example.demo.domain.TicketsRequestsAndResponce.CreateTicketsRequest;
import com.example.demo.domain.TicketsRequestsAndResponce.CreateTicketsResponse;
import com.example.demo.repository.FootballMatchEntity;
import com.example.demo.repository.TicketEntity;
import com.example.demo.repository.TicketsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateTicketUseCaseImplTest {
    @Mock
    TicketsRepository ticketsRepository;
    @InjectMocks
    CreateTicketUseCaseImpl createTicketUseCase;
    @Test
    void saveTicket_shouldReturn200(){


        TicketEntity expectedTicket = TicketEntity.builder()
                .price(200)
                .date("18-12-2022")
                .match(FootballMatchEntity.builder().id(1L).build())
                .place("Wembley")
                .quantity(50)
                .build();

        TicketEntity savedTicket = TicketEntity.builder()
                .id(1L)
                .place("Wembley")
                .quantity(50)
                .match(FootballMatchEntity.builder().id(1L).build())
                .date("18-12-2022")
                .price(200)
                .build();

        when(ticketsRepository.save(expectedTicket)).thenReturn(savedTicket);

        CreateTicketsRequest createTicketsRequest = CreateTicketsRequest.builder()
                .date("18-12-2022")
                .quantity(50)
                .place("Wembley")
                .price(200)
                .match(FootballMatch.builder().id(1L).build())
                .build();


        CreateTicketsResponse actualResponse = createTicketUseCase.createTicket(createTicketsRequest);
        CreateTicketsResponse expectedResponse = CreateTicketsResponse.builder()
                .id(1L)
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(ticketsRepository).save(expectedTicket);


    }
}
