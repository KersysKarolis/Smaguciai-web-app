package org.smaguciai.repositories;

import org.smaguciai.entities.Order;
import org.smaguciai.enumerators.OrderStatus;
import org.smaguciai.enumerators.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long > {
    Long countByStatus (OrderStatus status);
    List<Order> findByStatus(OrderStatus status);
    Optional<Order> findById (Order order);
    boolean existsByStartTimeLessThanAndEndTimeGreaterThanAndStatusAndPerformer(LocalDateTime end, LocalDateTime start, OrderStatus status, Performer performer);
}
