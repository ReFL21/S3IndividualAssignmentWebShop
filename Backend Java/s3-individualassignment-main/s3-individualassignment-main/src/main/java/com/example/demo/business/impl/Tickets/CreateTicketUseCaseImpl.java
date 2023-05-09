package com.example.demo.business.impl.Tickets;

import com.example.demo.business.CreateTicketUseCase;
import com.example.demo.domain.TicketsRequestsAndResponce.CreateTicketsRequest;
import com.example.demo.domain.TicketsRequestsAndResponce.CreateTicketsResponse;
import com.example.demo.repository.FootballMatchEntity;
import com.example.demo.repository.TicketEntity;
import com.example.demo.repository.TicketsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class CreateTicketUseCaseImpl implements CreateTicketUseCase {
        private final TicketsRepository ticketsRepository;


    @Override
    @Transactional
        public CreateTicketsResponse createTicket(CreateTicketsRequest ticketsRequest){
           /* if (ticketsRepository.existsByID(ticketsRequest.getId())){
                throw Exception;
            }*/
            TicketEntity ticketEntity = saveTicket(ticketsRequest);
            return CreateTicketsResponse.builder()
                    .id(ticketEntity.getId())
                    .build();
        }

        private TicketEntity saveTicket(CreateTicketsRequest ticketsRequest){
            TicketEntity ticketEntity = TicketEntity.builder()
                    .date(ticketsRequest.getDate())
                    .match(FootballMatchEntity.builder().id(ticketsRequest.getMatch().getId()).build())
                    .place(ticketsRequest.getPlace())
                    .price(ticketsRequest.getPrice())
                    .quantity(ticketsRequest.getQuantity())
                    .build();
            return ticketsRepository.save(ticketEntity);
        }

}
