package org.smaguciai.controllers;

import jakarta.validation.Valid;
import org.smaguciai.entities.Order;
import org.smaguciai.enumerators.Characters;
import org.smaguciai.enumerators.OrderGenre;
import org.smaguciai.enumerators.OrderStatus;
import org.smaguciai.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    private final OrderService service;

    public OrderController (OrderService orderService){
        this.service = orderService;
    }
    @GetMapping("/order")
    public String orderForm(Model model){
    model.addAttribute("order", new Order());
    model.addAttribute("character", Characters.values());
    model.addAttribute("genres", OrderGenre.values());
    return "order-form";
    }
    @PostMapping("/order")
    public String submitOrder(@Valid @ModelAttribute Order order, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
           return"order-form";
       }
        service.create(order);
        return "redirect:/";
    }
}
