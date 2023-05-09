package com.example.demo.business.TicketsTests;

import com.example.demo.business.impl.Tickets.DeleteTicketUseCaseImpl;
import com.example.demo.repository.TicketsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteTicketUseCaseImplTest {
    @Mock
    private TicketsRepository ticketsRepository;
    @InjectMocks
    DeleteTicketUseCaseImpl deleteTicketUseCase;

    @Test
    void deleteTicket_shouldDelete(){
        deleteTicketUseCase.deleteTicket(1L);
        verify(ticketsRepository).deleteById(1L);
    }

}
