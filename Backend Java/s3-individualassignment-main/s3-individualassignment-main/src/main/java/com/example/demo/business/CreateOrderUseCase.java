package com.example.demo.business;

import com.example.demo.domain.OrdersRequestsAndResponse.CreateOrderRequest;
import com.example.demo.domain.OrdersRequestsAndResponse.CreateOrderResponse;

public interface CreateOrderUseCase {
    CreateOrderResponse createOrder(CreateOrderRequest orderRequest);
}
