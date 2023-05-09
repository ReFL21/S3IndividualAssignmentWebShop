package com.example.demo.domain.TicketsRequestsAndResponce;

import com.example.demo.domain.Tickets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTicketsResponse {
   List<Tickets> tickets;

}
