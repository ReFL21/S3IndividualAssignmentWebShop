package com.example.demo.domain.OrdersRequestsAndResponse;

import com.example.demo.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetAllOrdersResponse {
    private List<Order> orders;
}
