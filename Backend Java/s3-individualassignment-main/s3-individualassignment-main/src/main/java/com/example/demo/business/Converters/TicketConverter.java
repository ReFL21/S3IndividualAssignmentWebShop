package com.example.demo.business.Converters;

import com.example.demo.domain.Tickets;
import com.example.demo.repository.TicketEntity;

public class TicketConverter {
    private TicketConverter(){

    }

    public static Tickets convert(TicketEntity ticket){
        return Tickets.builder()
                .id(ticket.getId())
                .match(MatchConverter.convert(ticket.getMatch()))
                .date(ticket.getDate())
                .place(ticket.getPlace())
                .price(ticket.getPrice())
                .quantity(ticket.getQuantity())
                .build();
    }
}
