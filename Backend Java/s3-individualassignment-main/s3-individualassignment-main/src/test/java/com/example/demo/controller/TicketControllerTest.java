package com.example.demo.controller;

import com.example.demo.business.CreateTicketUseCase;
import com.example.demo.business.DeleteTicketUseCase;
import com.example.demo.business.GetAllTicketsUseCase;
import com.example.demo.domain.FootballMatch;
import com.example.demo.domain.Tickets;
import com.example.demo.domain.TicketsRequestsAndResponce.CreateTicketsRequest;
import com.example.demo.domain.TicketsRequestsAndResponce.CreateTicketsResponse;
import com.example.demo.domain.TicketsRequestsAndResponce.GetAllTicketsResponse;
import com.example.demo.domain.TicketsRequestsAndResponce.GetTicketsByPlaceRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TicketControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreateTicketUseCase createTicketUseCase;
    @MockBean
    private DeleteTicketUseCase deleteTicketUseCase;
    @MockBean
    private GetAllTicketsUseCase getAllTicketsUseCase;

    @Test
    @WithMockUser(username = "Ivan40",roles = {"Admin"})
    void deleteTicket_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/tickets/5"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteTicketUseCase).deleteTicket(5L);
    }


    @Test
    @WithMockUser(username = "Ivan40",roles = {"Admin"})
    void createTicket_shouldReturn201_whenIsValid() throws Exception {
        CreateTicketsRequest request = CreateTicketsRequest.builder()
                .place("Home")
                .quantity(50)
                .price(50)
                .date("23-05-1995")
                .match(FootballMatch.builder().id(1L).build())
                .build();
        when(createTicketUseCase.createTicket(request)).thenReturn(CreateTicketsResponse.builder().id(1L).build());

        mockMvc.perform(post("/tickets/create").contentType(APPLICATION_JSON_VALUE).content(
                """
                    {    
                      "date":"23-05-1995",  
                      "place":"Home",
                      "match":{"id":1},
                        "price":50,
                        "quantity":50
                     }   
                        """


        )).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type",APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                      
                                 {"id": 1}
                                 """
                ));


        verify(createTicketUseCase).createTicket(request);

    }

    @Test
    @WithMockUser(username = "Ivan40",roles = {"Admin"})
    void getAllTickets_shouldReturnListOfTickets() throws Exception {
        GetAllTicketsResponse response = GetAllTicketsResponse.builder()
                .tickets(List.of(
                        Tickets.builder().id(1l).date("15-05-2023").place("stadium1").match(FootballMatch.builder().id(12L).build()).build(),
                        Tickets.builder().id(4l).date("15-05-2023").place("stadium1").match(FootballMatch.builder().id(12L).build()).build()
                ))
                .build();
        GetTicketsByPlaceRequest request = GetTicketsByPlaceRequest.builder().build();
        lenient().when(getAllTicketsUseCase.findGames(request)).thenReturn(response);

        mockMvc.perform(get("/tickets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                        {
                        "tickets": [
                        {
                        "id": 1,
                        "date": "15-05-2023",
                        "place": "stadium1",
                        "match": {
                        "id": 12
                        }
                        
                        
                        },
                         {
                        "id": 4,
                        "date": "15-05-2023",
                        "place": "stadium1",
                        "match": {
                        "id": 12
                        }
                       
                        
                        }
                        
                        ]
                        }
                        """



                ));


        verify(getAllTicketsUseCase).findGames(request);
    }


}
