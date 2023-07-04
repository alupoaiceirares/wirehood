package org.wirehood.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wirehood.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
