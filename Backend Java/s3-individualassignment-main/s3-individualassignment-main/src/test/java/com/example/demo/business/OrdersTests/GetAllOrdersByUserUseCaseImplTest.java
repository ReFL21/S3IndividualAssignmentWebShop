package com.example.demo.business.OrdersTests;

import com.example.demo.business.impl.Orders.GetOrdersByUserIdUseCaseImpl;
import com.example.demo.domain.FootballMatch;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrdersRequestsAndResponse.GetOrdersByUserIdResponse;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllOrdersByUserUseCaseImplTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private GetOrdersByUserIdUseCaseImpl getOrdersByUserIdUseCase;
    @Test
    void getOrders_shouldReturnListOfOrders(){
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
        when(orderRepository.findOrderEntitiesByUserId(1L)).thenReturn(List.of(orderEntity1, orderEntity2));

        GetOrdersByUserIdResponse actualResponse = getOrdersByUserIdUseCase.getOrdersByUserId(1L);

        Order orderDTO = Order.builder()
                .id(1L)
                .user(User.builder().id(1L).build())
                .tickets(Tickets.builder().id(3L).match(FootballMatch.builder().id(2L).build()).build())
                .quantity(2)
                .date("12-02-2023")
                .build();

        Order order2DTO = Order.builder()
                .id(2L)
                .user(User.builder().id(1L).build())
                .tickets(Tickets.builder().id(3L).match(FootballMatch.builder().id(2L).build()).build())
                .quantity(5)
                .date("12-02-2023")
                .build();

        GetOrdersByUserIdResponse expectedResponse = GetOrdersByUserIdResponse.builder()
                .orders(List.of(orderDTO, order2DTO))
                .build();

        assertEquals(expectedResponse, actualResponse);

    }


}
