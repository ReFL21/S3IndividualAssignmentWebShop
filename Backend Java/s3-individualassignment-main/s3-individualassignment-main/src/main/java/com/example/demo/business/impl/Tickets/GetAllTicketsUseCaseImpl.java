package com.example.demo.business.impl.Tickets;

import com.example.demo.business.Converters.TicketConverter;
import com.example.demo.business.GetAllTicketsUseCase;
import com.example.demo.domain.Tickets;
import com.example.demo.domain.TicketsRequestsAndResponce.GetAllTicketsResponse;
import com.example.demo.domain.TicketsRequestsAndResponce.GetTicketsByPlaceRequest;
import com.example.demo.repository.TicketEntity;
import com.example.demo.repository.TicketsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class GetAllTicketsUseCaseImpl implements GetAllTicketsUseCase {

    private TicketsRepository ticketsRepository;

    @Override
    @Transactional
    public GetAllTicketsResponse getAllTickets() {
        List<Tickets> result;
        result = ticketsRepository.findAll()
                .stream()
                .map(TicketConverter::convert)
                .toList();

        return GetAllTicketsResponse.builder()
                .tickets(result)
                .build();
    }

    @Override
    public GetAllTicketsResponse findGames(final GetTicketsByPlaceRequest request){
        List<TicketEntity> tickets;

        if (StringUtils.hasText(request.getPlace())){
            tickets = ticketsRepository.findTicketEntityByPlace(request.getPlace());
        }else{
            tickets = ticketsRepository.findAll();
        }
        final GetAllTicketsResponse response = new GetAllTicketsResponse();
        List<Tickets> ticketsDTO = tickets
                .stream()
                .map(TicketConverter::convert)
                .toList();
        response.setTickets(ticketsDTO);
        return response;
    }

}
