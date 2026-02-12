package org.smaguciai.services;

import jakarta.transaction.Transactional;
import org.smaguciai.entities.Order;
import org.smaguciai.enumerators.OrderStatus;
import org.smaguciai.enumerators.Performer;
import org.smaguciai.events.OrderApprovedEvent;
import org.smaguciai.repositories.OrderRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final OrderRepository repository;
    private final SimpMessagingTemplate messagingTemplate;

    public OrderService(OrderRepository repository, SimpMessagingTemplate messagingTemplate, ApplicationEventPublisher publisher){
        this.repository = repository;
        this.messagingTemplate = messagingTemplate;
        this.applicationEventPublisher=publisher;
    }
    public Order create (Order order){
        order.setStatus(OrderStatus.LAUKIAMAS);
        Order saved = repository.save(order);
        messagingTemplate.convertAndSend("/topic/orders/pending", saved);
        return saved;
    }
    @Transactional
    public Order updateStatusAndPerfomer(Long id, OrderStatus orderStatus, Performer performer){
        Order updatedOrder = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found" + id));
        updatedOrder.setStatus(orderStatus);
        updatedOrder.setPerformer(performer);
        if(updatedOrder.getStatus() == OrderStatus.PRIIMTAS){
            messagingTemplate.convertAndSend("/topic/orders/approved", updatedOrder);
        }
        applicationEventPublisher.publishEvent(new OrderApprovedEvent(updatedOrder));
        return updatedOrder;
    }
    @Transactional
    public Order rejectOrder(Long id, OrderStatus orderStatus){
        Order updatedOrder = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found" + id));
        updatedOrder.setStatus(orderStatus);
        return updatedOrder;
    }
    @Transactional
    public Order updateOrder(Long id, Order order){
        Order updatedOrder = repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Order not found"));
        updatedOrder.setChildName(order.getChildName());
        updatedOrder.setOrderGenre(order.getOrderGenre());
        updatedOrder.setStatus(order.getStatus());
        updatedOrder.setPerformer(order.getPerformer());
        updatedOrder.setAge(order.getAge());
        updatedOrder.setAmountOfChildren(order.getAmountOfChildren());
        updatedOrder.setCharacter(order.getCharacter());
        updatedOrder.setLocation(order.getLocation());
        updatedOrder.setEmail(order.getEmail());
        updatedOrder.setNotes(order.getNotes());
        updatedOrder.setPhoneNumber(order.getPhoneNumber());
       return updatedOrder;
    }
    public void deleteOrder(Long id){
        repository.deleteById(id);
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
