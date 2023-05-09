package com.example.demo.business.OrdersTests;

import com.example.demo.business.impl.Orders.DeleteOrderUseCaseImpl;
import com.example.demo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteOrderUseCaseImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    DeleteOrderUseCaseImpl deleteOrderUseCase;

    @Test
    void deleteOrder_shouldDeleteOrder(){
        deleteOrderUseCase.deleteOrder(1L);
        verify(orderRepository).deleteById(1L);
    }

}
