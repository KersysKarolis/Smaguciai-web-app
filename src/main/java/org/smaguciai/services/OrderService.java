package org.smaguciai.services;

import jakarta.transaction.Transactional;
import org.smaguciai.converters.OrderConverter;
import org.smaguciai.dto.CreateOrderDto;
import org.smaguciai.dto.OrderResponseDto;
import org.smaguciai.dto.UpdateOrderDto;
import org.smaguciai.entities.Order;
import org.smaguciai.enumerators.OrderStatus;
import org.smaguciai.enumerators.Performer;
import org.smaguciai.events.OrderApprovedEvent;
import org.smaguciai.repositories.OrderRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final OrderRepository repository;
    private final SimpMessagingTemplate messagingTemplate;
    private final OrderConverter converter;

    public OrderService(OrderConverter converter, OrderRepository repository, SimpMessagingTemplate messagingTemplate, ApplicationEventPublisher publisher){
        this.repository = repository;
        this.messagingTemplate = messagingTemplate;
        this.applicationEventPublisher=publisher;
        this.converter = converter;
    }
    @Transactional
    public Order create (CreateOrderDto orderDto){
        Order order = converter.createOrderDtoToOrder(orderDto);
        validateTimeRange(order.getStartTime(), order.getEndTime());
        order.setStatus(OrderStatus.LAUKIAMAS);
        Order saved = repository.save(order);
        messagingTemplate.convertAndSend("/topic/orders/pending", saved);
        return saved;
    }
    @Transactional
    public Order updateStatusAndPerfomer(Long id, OrderStatus orderStatus, Performer performer){
        Order updatedOrder = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found" + id));
        validateNoTimeConflict(updatedOrder.getStartTime(), updatedOrder.getEndTime(), performer);
        updatedOrder.setStatus(orderStatus);
        updatedOrder.setPerformer(performer);
        if(updatedOrder.getStatus() == OrderStatus.PRIIMTAS){
            messagingTemplate.convertAndSend("/topic/orders/approved", updatedOrder);
        }
        if(updatedOrder.getStatus() == OrderStatus.PRIIMTAS) {
            applicationEventPublisher.publishEvent(new OrderApprovedEvent(updatedOrder));
        }
        return updatedOrder;
    }
    @Transactional
    public OrderResponseDto rejectOrder(Long id, OrderStatus orderStatus){
        Order updatedOrder = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found" + id));
        updatedOrder.setStatus(orderStatus);
        return converter.orderToOrderResponseDto(updatedOrder);
    }
    @Transactional
    public OrderResponseDto updateOrder(Long id, UpdateOrderDto order){
        if(order.getStartTime().isAfter(order.getEndTime())) {
        throw new IllegalArgumentException("Neteisingas laikas");
        }
        Order updatedOrder = repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Order not found"));
        converter.updateOrderDtoToOrder(updatedOrder, order);
       return converter.orderToOrderResponseDto(updatedOrder);
    }
    public void deleteOrder(Long id){
        repository.deleteById(id);
    }
    private void validateTimeRange(LocalDateTime start, LocalDateTime end){
        if(start == null || end ==null){
            throw new IllegalArgumentException("Privalote nurodyti laiką");
        }
        if(start.isAfter(end) || start.isEqual(end)){
            throw new IllegalArgumentException("Neteisingas laiko intervalas");
        }
        if(start.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Pasirinktas laikas baigėsi");
        }
    }
    private void validateNoTimeConflict(LocalDateTime start, LocalDateTime end, Performer performer){
        boolean exists = repository.existsByStartTimeLessThanAndEndTimeGreaterThanAndStatusAndPerformer(end,start,OrderStatus.PRIIMTAS,performer);
        if(exists){
            throw new IllegalStateException("Pasirinktas laikas užimtas");
        }
    }
    public List<Order> orderList (){
        return repository.findAll();
    }
   // public List<Order> approvedOrders (){
   //     List<Order> orders = orderList();
   //     List<Order> approvedOrders = new ArrayList<>();
   //     for(Order e: orders){
   //         if (e.getStatus() == OrderStatus.PRIIMTAS){
   //             approvedOrders.add(e);
   //         }
   //     }
   //     return approvedOrders;
   // }
    public List<Order> getOrdersByStatus (OrderStatus status){
        return repository.findByStatus(status);
    }
    public Long pendingOrdersCount(OrderStatus status){
        return repository.countByStatus(status);
    }

    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }
}
