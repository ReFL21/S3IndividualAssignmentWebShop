package com.example.demo.business.impl.Tickets;

import com.example.demo.business.Converters.TicketConverter;
import com.example.demo.business.GetTicketsByPlaceUseCase;
import com.example.demo.domain.Tickets;
import com.example.demo.domain.TicketsRequestsAndResponce.GetTicketsByPlaceRequest;
import com.example.demo.domain.TicketsRequestsAndResponce.GetTicketsByPlaceResponse;
import com.example.demo.repository.TicketEntity;
import com.example.demo.repository.TicketsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class GetTicketsByPlaceUseCaseImpl implements GetTicketsByPlaceUseCase {
    private final TicketsRepository ticketsRepository;

@Override
public GetTicketsByPlaceResponse findGames(final GetTicketsByPlaceRequest request){
    List<TicketEntity> tickets;

    if (StringUtils.hasText(request.getPlace())){
        tickets = ticketsRepository.findTicketEntityByPlace(request.getPlace());
    }else{
        tickets = ticketsRepository.findAll();
    }
    final GetTicketsByPlaceResponse response = new GetTicketsByPlaceResponse();
    List<Tickets> ticketsDTO = tickets
            .stream()
            .map(TicketConverter::convert)
            .toList();
    response.setTickets(ticketsDTO);
    return response;
}

}
