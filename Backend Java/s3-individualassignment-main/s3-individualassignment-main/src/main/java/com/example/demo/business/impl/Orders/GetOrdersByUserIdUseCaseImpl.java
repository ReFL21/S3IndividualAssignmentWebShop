package com.example.demo.business.impl.Orders;

import com.example.demo.business.Converters.OrderConverter;
import com.example.demo.business.GetOrdersByUserIdUseCase;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrdersRequestsAndResponse.GetOrdersByUserIdResponse;
import com.example.demo.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetOrdersByUserIdUseCaseImpl implements GetOrdersByUserIdUseCase {
    private OrderRepository orderRepository;

    @Override
    public GetOrdersByUserIdResponse getOrdersByUserId(Long id) {
        List<Order> order;
        order = orderRepository.findOrderEntitiesByUserId(id)
                .stream()
                .map(OrderConverter::convert)
                .toList();


        return GetOrdersByUserIdResponse.builder()
                .orders(order)
                .build();

    }
}
