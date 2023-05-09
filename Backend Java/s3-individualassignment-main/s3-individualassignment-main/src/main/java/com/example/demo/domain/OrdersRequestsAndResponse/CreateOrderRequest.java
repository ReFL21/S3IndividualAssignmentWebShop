package com.example.demo.domain.OrdersRequestsAndResponse;

import com.example.demo.domain.Tickets;
import com.example.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequest {
    @NotNull
    private Tickets ticket;

    @NotNull
    private User user;
    @NotBlank
    private String date;

    @NotNull
    private long quantity;

//    @NotNull
//    private long price;
}
