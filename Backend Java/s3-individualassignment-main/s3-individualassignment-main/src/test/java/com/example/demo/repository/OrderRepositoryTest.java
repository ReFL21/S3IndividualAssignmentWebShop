package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void save_addOrderWithFields(){
        FootballMatchEntity match = FootballMatchEntity.builder()
                .firstTeam("MyTeam")
                .secondTeam("YourTeam")
                .date("15-05-2023")
                .build();
        entityManager.persist(match);
        TicketEntity ticket = TicketEntity.builder()
                .date("15-05-2023")
                .match(match)
                .place("Stadium1")
                .quantity(50)
                .build();
        entityManager.persist(ticket);
        UserEntity user = UserEntity.builder()
                .username("Ivan40")
                .email("ivan40@gmail.com")
                .name("Ivan")
                .password("asdasd")
                .build();
        entityManager.persist(user);
        OrderEntity order = OrderEntity.builder()
                .id(1L)
                .tickets(ticket)
                .user(user)
                .price(50)
                .date("15-02-2023")
                .quantity(1)
                .build();

        OrderEntity savedOrder = orderRepository.save(order);
        assertNotNull(savedOrder.getId());
        savedOrder = entityManager.find(OrderEntity.class, savedOrder.getId());

        OrderEntity expectedOrder = OrderEntity.builder()
                .id(1L)
                .tickets(ticket)
                .user(user)
                .price(50)
                .date("15-02-2023")
                .quantity(1)
                .build();

        assertEquals(expectedOrder, savedOrder);
    }

    @Test
    void save_addOrderWithoutFields_throwException(){
    OrderEntity empty = OrderEntity.builder().build();
        assertThrows(ConstraintViolationException.class, () -> orderRepository.save(empty));
    }
}
