package com.example.demo.business.OrdersTests;

import com.example.demo.business.impl.Orders.CreateOrderUserCaseImpl;
import com.example.demo.domain.OrdersRequestsAndResponse.CreateOrderRequest;
import com.example.demo.domain.OrdersRequestsAndResponse.CreateOrderResponse;
import com.example.demo.domain.Tickets;
import com.example.demo.domain.User;
import com.example.demo.repository.OrderEntity;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.TicketEntity;
import com.example.demo.repository.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateOrderUseCaseImplTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    CreateOrderUserCaseImpl createOrderUserCase;

@Test
    void saveOrder_should200(){
UserEntity userEntity = UserEntity.builder()
        .id(1L)
        .build();
    TicketEntity ticketEntity = TicketEntity.builder()
            .id(1L)
//            .match(FootballMatchEntity.builder()
//                    .id(1l)
//                    .build())
            .build();
        OrderEntity expectedOrder = OrderEntity.builder()
              //  .price(100)
                .user(userEntity)
                .tickets(ticketEntity)
                .quantity(2)
                .date("28-12-2022")
                .build();



        OrderEntity savedOrder = OrderEntity.builder()
                .id(1L)
                .price(100)
                .user(userEntity)
                .tickets(ticketEntity)
                .quantity(2)
                .date("28-12-2022")
                .build();


        when(orderRepository.save(expectedOrder)).thenReturn(savedOrder);

        User userDTO = User.builder()
                .id(1L)
                .build();

        Tickets ticketDTO = Tickets.builder()
                .id(1L)
//                .match(FootballMatch.builder()
//                        .id(1l)
//                        .build())
                .build();


        CreateOrderRequest request = CreateOrderRequest.builder()
                .user(userDTO)
                .ticket(ticketDTO)
                .quantity(2)
                .date("28-12-2022")
                .build();

        CreateOrderResponse expectedResponse = createOrderUserCase.createOrder(request);

        CreateOrderResponse actualResponse = CreateOrderResponse.builder()
                .id(1L)
                .build();
        assertEquals( expectedResponse, actualResponse);
        verify(orderRepository).save(expectedOrder);
    }
}
