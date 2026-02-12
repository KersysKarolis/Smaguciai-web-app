package org.smaguciai.repositories;

import org.smaguciai.entities.Order;
import org.smaguciai.enumerators.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long > {
    Long countByStatus (OrderStatus status);
    List<Order> findByStatus(OrderStatus status);
    Optional<Order> findById (Order order);
}
