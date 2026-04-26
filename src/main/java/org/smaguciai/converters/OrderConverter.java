package org.smaguciai.converters;

import org.smaguciai.dto.CreateOrderDto;
import org.smaguciai.dto.OrderResponseDto;
import org.smaguciai.dto.UpdateOrderDto;
import org.smaguciai.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {
    public Order createOrderDtoToOrder(CreateOrderDto dto){
        if(dto == null) return null;
        Order order = new Order();
        order.setChildName(dto.getChildName());
        order.setNotes(dto.getNotes());
        order.setOrderGenre(dto.getOrderGenre());
        order.setLocation(dto.getLocation());
        order.setEmail(dto.getEmail());
        order.setCharacter(dto.getCharacter());
        order.setAmountOfChildren(dto.getAmountOfChildren());
        order.setAge(dto.getAge());
        order.setStartTime(dto.getStartTime());
        order.setEndTime(dto.getEndTime());
        order.setPhoneNumber(dto.getPhoneNumber());
        order.setNotes(dto.getNotes());
        order.setTitle(dto.getTitle());
        order.setPerformer(null);
        return order;
    }
    public void updateOrderDtoToOrder(Order order, UpdateOrderDto dto){
        if(dto == null || order == null) return;
        if(dto.getChildName() != null)
            order.setChildName(dto.getChildName());
        if(dto.getAge() != null)
            order.setAge(dto.getAge());
        if(dto.getEmail() !=null)
            order.setEmail(dto.getEmail());
        if(dto.getLocation() != null)
            order.setLocation(dto.getLocation());
        if(dto.getAmountOfChildren() != null)
            order.setAmountOfChildren(dto.getAmountOfChildren());
        if(dto.getOrderGenre()!=null)
            order.setOrderGenre(dto.getOrderGenre());
        if(dto.getPerformer()!=null)
            order.setPerformer(dto.getPerformer());
        if(dto.getNotes()!=null)
            order.setNotes(dto.getNotes());
        if(dto.getStatus()!=null)
            order.setStatus(dto.getStatus());
        if(dto.getStartTime()!=null)
            order.setStartTime(dto.getStartTime());
        if(dto.getTitle()!=null)
            order.setTitle(dto.getTitle());
        if(dto.getEndTime()!=null)
            order.setEndTime(dto.getEndTime());
        if(dto.getCharacter()!=null)
            order.setCharacter(dto.getCharacter());
        if(dto.getPhoneNumber()!=null)
            order.setPhoneNumber(dto.getPhoneNumber());

    }
    public OrderResponseDto orderToOrderResponseDto(Order order){
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setAge(order.getAge());
        dto.setCharacter(order.getCharacter());
        dto.setLocation(order.getLocation());
        dto.setEmail(order.getEmail());
        dto.setNotes(order.getNotes());
        dto.setOrderGenre(order.getOrderGenre());
        dto.setChildName(order.getChildName());
        dto.setAmountOfChildren(order.getAmountOfChildren());
        dto.setPhoneNumber(order.getPhoneNumber());
        dto.setTitle(order.getTitle());
        dto.setStartTime(order.getStartTime());
        dto.setEndTime(order.getEndTime());
        dto.setPerformer(order.getPerformer());
        dto.setStatus(order.getStatus());
        return dto;
    }

}
