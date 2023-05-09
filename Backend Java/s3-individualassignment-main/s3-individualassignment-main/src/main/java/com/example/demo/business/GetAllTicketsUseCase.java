package com.example.demo.business;


import com.example.demo.domain.TicketsRequestsAndResponce.GetAllTicketsResponse;
import com.example.demo.domain.TicketsRequestsAndResponce.GetTicketsByPlaceRequest;

public interface GetAllTicketsUseCase {
    GetAllTicketsResponse getAllTickets();
    GetAllTicketsResponse findGames(GetTicketsByPlaceRequest request);
}
