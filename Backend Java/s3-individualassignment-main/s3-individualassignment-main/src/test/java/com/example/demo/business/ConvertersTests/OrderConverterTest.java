package com.example.demo.business.ConvertersTests;

import com.example.demo.business.Converters.OrderConverter;
import com.example.demo.domain.FootballMatch;
import com.example.demo.domain.Order;
import com.example.demo.domain.Tickets;
import com.example.demo.domain.User;
import com.example.demo.repository.FootballMatchEntity;
import com.example.demo.repository.OrderEntity;
import com.example.demo.repository.TicketEntity;
import com.example.demo.repository.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderConverterTest {
    @Test
    void shouldConvertOrderEntityToOrder(){
        OrderEntity order = OrderEntity.builder()
                .id(21L)
                .price(100)
                .user(UserEntity.builder().id(1L).build())
                .tickets(TicketEntity.builder().id(1L).match(FootballMatchEntity.builder().id(1l).build()).build())
                .quantity(2)
                .date("28-12-2022")
                .build();

        Order actualOrder = OrderConverter.convert(order);

        Order expectedOrder = Order.builder()
                .id(21L)
                .price(100)
                .user(User.builder().id(1L).build())
                .tickets(Tickets.builder().id(1L).match(FootballMatch.builder().id(1l).build()).build())
                .quantity(2)
                .date("28-12-2022")
                .build();


        assertEquals(expectedOrder, actualOrder);
    }



}
