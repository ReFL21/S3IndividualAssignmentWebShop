package com.example.demo.controller;

import com.example.demo.business.CreateTicketUseCase;
import com.example.demo.business.DeleteTicketUseCase;
import com.example.demo.business.GetAllTicketsUseCase;
import com.example.demo.business.GetTicketsByPlaceUseCase;
import com.example.demo.configuration.security.Isauthenticated.IsAuthenticated;
import com.example.demo.domain.TicketsRequestsAndResponce.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@AllArgsConstructor
@RequestMapping("/tickets")
public class TicketsController {
    @Autowired
    private final GetAllTicketsUseCase getAllTicketsUseCase;

    @Autowired
    private final CreateTicketUseCase createTicketUseCase;

    @Autowired
    private final DeleteTicketUseCase deleteTicketUseCase;
    @Autowired
    private final GetTicketsByPlaceUseCase getTicketsByPlaceUseCase;
    @IsAuthenticated
    @RolesAllowed({"Admin", "Customer"})
    @GetMapping
    public ResponseEntity<GetAllTicketsResponse> getAllTickets(@RequestParam(value = "place", required = false)String place){
    // GetAllTicketsResponse response = getAllTicketsUseCase.getAllTickets();
        GetTicketsByPlaceRequest request = GetTicketsByPlaceRequest.builder()
                .place(place)
                .build();
        GetAllTicketsResponse response = getAllTicketsUseCase.findGames(request);

     return ResponseEntity.ok(response);
    }

    @IsAuthenticated
    @RolesAllowed({"Admin"})
    @PostMapping("/create")
    public ResponseEntity<CreateTicketsResponse> createTicket(@RequestBody @Valid CreateTicketsRequest ticketsRequest){
    CreateTicketsResponse ticketsResponse = createTicketUseCase.createTicket(ticketsRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(ticketsResponse);
    }
    @IsAuthenticated
    @RolesAllowed({"Admin"})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id){
        deleteTicketUseCase.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
    @IsAuthenticated
    @RolesAllowed({"Admin", "Customer"})
    @GetMapping("{place}")
    public ResponseEntity<GetTicketsByPlaceResponse> getTicketsByPlace(@RequestParam(value = "place", required = false)String place){
        GetTicketsByPlaceRequest request = GetTicketsByPlaceRequest.builder()
                .place(place)
                .build();
        GetTicketsByPlaceResponse response = getTicketsByPlaceUseCase.findGames(request);
        return ResponseEntity.ok(response);
    }




}
