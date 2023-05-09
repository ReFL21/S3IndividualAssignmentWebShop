package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

   // OrderEntity save(OrderEntity OrderEntity);
  //  List<OrderEntity> findAll();
    List<OrderEntity> findOrderEntitiesByUserId(Long user_id);

}
