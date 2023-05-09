package com.example.demo.business.impl.Orders;

import com.example.demo.business.DeleteOrderUseCase;
import com.example.demo.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class DeleteOrderUseCaseImpl implements DeleteOrderUseCase {
private final OrderRepository orderRepository;
    @Override
    @Transactional
    public void deleteOrder(Long id) {
        this.orderRepository.deleteById(id);
    }
}
