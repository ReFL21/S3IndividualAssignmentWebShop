package com.example.demo.business.impl.Orders;

import com.example.demo.business.Converters.OrderConverter;
import com.example.demo.business.GetAllOrdersUseCase;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrdersRequestsAndResponse.GetAllOrdersResponse;
import com.example.demo.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GetAllOrdersUseCaseImpl implements GetAllOrdersUseCase {
    private OrderRepository orderRepository;

    @Transactional
    public GetAllOrdersResponse getAllOrders(){
        List<Order> orderEntities;
        orderEntities = orderRepository.findAll()
                .stream()
                .map(OrderConverter::convert)
                .toList();

        return GetAllOrdersResponse.builder()
                .orders(orderEntities)
                .build();
    }



}
