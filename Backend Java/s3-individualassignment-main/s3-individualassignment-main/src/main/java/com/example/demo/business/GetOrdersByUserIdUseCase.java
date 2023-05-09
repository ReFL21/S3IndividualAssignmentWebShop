package com.example.demo.business;

import com.example.demo.domain.OrdersRequestsAndResponse.GetOrdersByUserIdResponse;

public interface GetOrdersByUserIdUseCase {
GetOrdersByUserIdResponse getOrdersByUserId(Long id);
}
