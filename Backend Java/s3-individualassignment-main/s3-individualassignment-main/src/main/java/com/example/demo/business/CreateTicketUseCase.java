package com.example.demo.business;

import com.example.demo.domain.TicketsRequestsAndResponce.CreateTicketsRequest;
import com.example.demo.domain.TicketsRequestsAndResponce.CreateTicketsResponse;

public interface CreateTicketUseCase {
    CreateTicketsResponse createTicket(CreateTicketsRequest ticketsRequest);
}
