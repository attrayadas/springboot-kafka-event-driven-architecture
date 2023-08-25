package com.attraya.stockservice.repository;

import com.attraya.stockservice.entity.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEvent, String> {
}
