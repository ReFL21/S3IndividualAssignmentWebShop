package com.example.demo.business.OrdersTests;

import com.example.demo.business.impl.Orders.GetAllOrdersUseCaseImpl;
import com.example.demo.domain.FootballMatch;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrdersRequestsAndResponse.GetAllOrdersResponse;
import com.example.demo.domain.Tickets;
import com.example.demo.domain.User;
import com.example.demo.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllOrders {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private GetAllOrdersUseCaseImpl getAllOrders;

    @Test
    void getAllOrders_shouldReturnOrders(){



        OrderEntity orderEntity1 = OrderEntity.builder()
                .id(1L)
                .user(UserEntity.builder().id(1L).build())
                .tickets(TicketEntity.builder().id(3L).match(FootballMatchEntity.builder().id(2L).build()).build())
                .quantity(2)
                .date("12-02-2023")
                .build();


        OrderEntity orderEntity2 = OrderEntity.builder()
                .id(2L)
                .user(UserEntity.builder().id(1L).build())
                .tickets(TicketEntity.builder().id(3L).match(FootballMatchEntity.builder().id(2L).build()).build())
                .quantity(5)
                .date("12-02-2023")
                .build();

        when(orderRepository.findAll()).thenReturn(List.of(orderEntity1, orderEntity2));

        GetAllOrdersResponse actualResponse = getAllOrders.getAllOrders();

        Order orderDTO1 = Order.builder()
                .id(1L)
                .user(User.builder().id(1L).build())
                .tickets(Tickets.builder().id(3L).match(FootballMatch.builder().id(2L).build()).build())
                .quantity(2)
                .date("12-02-2023")
                .build();

        Order orderDTO2 = Order.builder()
                .id(2L)
                .user(User.builder().id(1L).build())
                .tickets(Tickets.builder().id(3L).match(FootballMatch.builder().id(2L).build()).build())
                .quantity(5)
                .date("12-02-2023")
                .build();

        GetAllOrdersResponse expectedResponse = GetAllOrdersResponse.builder()
                .orders(List.of(orderDTO1, orderDTO2))
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(orderRepository).findAll();

    }
}
