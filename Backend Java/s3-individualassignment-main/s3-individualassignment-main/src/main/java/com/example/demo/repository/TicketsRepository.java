package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TicketsRepository extends JpaRepository<TicketEntity, Long> {


    boolean existsById(Long id);

 //  Optional<TicketEntity> findById(Long id);
    TicketEntity save(TicketEntity ticketEntity);
        List<TicketEntity> findTicketEntityByPlace(String place);

}
