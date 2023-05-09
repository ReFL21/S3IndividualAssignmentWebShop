package com.example.demo.business;

import com.example.demo.domain.TicketsRequestsAndResponce.GetTicketsByPlaceRequest;
import com.example.demo.domain.TicketsRequestsAndResponce.GetTicketsByPlaceResponse;

public interface GetTicketsByPlaceUseCase {
    GetTicketsByPlaceResponse findGames(GetTicketsByPlaceRequest request);
}
