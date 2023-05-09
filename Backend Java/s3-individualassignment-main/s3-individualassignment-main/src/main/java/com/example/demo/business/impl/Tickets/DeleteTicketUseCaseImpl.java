package com.example.demo.business.impl.Tickets;

import com.example.demo.business.DeleteTicketUseCase;
import com.example.demo.repository.TicketsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class DeleteTicketUseCaseImpl implements DeleteTicketUseCase {
    private final TicketsRepository ticketsRepository;

    @Override
    @Transactional
    public void deleteTicket(Long id){
        this.ticketsRepository.deleteById(id);
    }

}
