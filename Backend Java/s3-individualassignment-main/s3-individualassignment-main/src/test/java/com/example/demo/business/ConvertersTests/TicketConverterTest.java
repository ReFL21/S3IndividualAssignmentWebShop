package com.example.demo.business.ConvertersTests;

import com.example.demo.business.Converters.TicketConverter;
import com.example.demo.domain.FootballMatch;
import com.example.demo.domain.Tickets;
import com.example.demo.repository.FootballMatchEntity;
import com.example.demo.repository.TicketEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketConverterTest {
    @Test
    void shouldConvertTicketEntityToTicket(){
        TicketEntity ticketEntity = TicketEntity.builder()
                .id(12L)
                .place("Home")
                .quantity(50)
                .price(50)
                .date("23-05-1995")
                .match(FootballMatchEntity.builder().id(1L).build())
                .build();

        Tickets actualTicket = TicketConverter.convert(ticketEntity);

        Tickets expectedTicket = Tickets.builder()
                .id(12L)
                .place("Home")
                .quantity(50)
                .price(50)
                .date("23-05-1995")
                .match(FootballMatch.builder().id(1L).build())
                .build();

        assertEquals(expectedTicket, actualTicket);

    }


}
