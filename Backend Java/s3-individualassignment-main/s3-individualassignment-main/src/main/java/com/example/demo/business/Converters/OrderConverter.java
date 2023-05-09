package com.example.demo.business.Converters;

import com.example.demo.domain.Order;
import com.example.demo.repository.OrderEntity;

public class OrderConverter {
    private OrderConverter(){}

    public static Order convert(OrderEntity order){
        return Order.builder()
                .id(order.getId())
                .tickets(TicketConverter.convert(order.getTickets()))
                .user(UserConverter.convert(order.getUser()))
                .date(order.getDate())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .build();
    }

}
