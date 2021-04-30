package com.example.springsocial.repository;

import com.example.springsocial.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order,Long> {
}
