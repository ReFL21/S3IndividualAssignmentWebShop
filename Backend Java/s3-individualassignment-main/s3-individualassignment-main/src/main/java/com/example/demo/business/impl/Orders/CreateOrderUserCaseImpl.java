package com.example.demo.business.impl.Orders;

import com.example.demo.business.CreateOrderUseCase;
import com.example.demo.domain.OrdersRequestsAndResponse.CreateOrderRequest;
import com.example.demo.domain.OrdersRequestsAndResponse.CreateOrderResponse;
import com.example.demo.repository.OrderEntity;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.TicketEntity;
import com.example.demo.repository.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class CreateOrderUserCaseImpl implements CreateOrderUseCase {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest orderRequest) {
        OrderEntity orderEntity = saveOrder(orderRequest);
        return CreateOrderResponse.builder()
                .id(orderEntity.getId())
                .build();
    }


    private OrderEntity saveOrder(CreateOrderRequest orderRequest){
        OrderEntity order = OrderEntity.builder()
                .user(UserEntity.builder().id(orderRequest.getUser().getId()).build())
                .tickets(TicketEntity.builder().id(orderRequest.getTicket().getId()).build())
                .date(orderRequest.getDate())
                .quantity(orderRequest.getQuantity())
                .price(orderRequest.getQuantity()*orderRequest.getTicket().getPrice())
                .build();
        return orderRepository.save(order);
    }
}
