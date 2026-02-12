package org.smaguciai.controllers;

import org.smaguciai.entities.Content;
import org.smaguciai.entities.HomeImage;
import org.smaguciai.enumerators.OrderStatus;
import org.smaguciai.repositories.HomeContentRepository;
import org.smaguciai.repositories.HomeImageRepository;
import org.smaguciai.services.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
public class AdminController {

    private final OrderService service;
    private final HomeContentRepository repository;
    private final HomeImageRepository imageRepository;

    public AdminController(OrderService service, HomeContentRepository repository, HomeImageRepository imgRepository) {
        this.service = service;
        this.repository = repository;
        this.imageRepository=imgRepository;
    }

    @GetMapping("/admin/orders")
    public String orders(Model model){
        model.addAttribute("orders", service.getOrdersByStatus(OrderStatus.PRIIMTAS));
        model.addAttribute("pendingOrderCount", service.pendingOrdersCount(OrderStatus.LAUKIAMAS));
        model.addAttribute("pendingOrders", service.getOrdersByStatus(OrderStatus.LAUKIAMAS));

        return "admin-orders";
    }
    @GetMapping("/admin/orders/{id}")
    public String viewOrder(@PathVariable Long id, Model model){
        model.addAttribute("order", service.findById(id).orElseThrow(()-> new RuntimeException("Order not found")));
        return "admin-order-view";
    }
    @GetMapping("/admin/home")
    public String editHome(Model model){
        model.addAttribute("heroTitle",
                repository.findById("hero.hero.title").map(Content::getAllContent).orElseThrow());
        model.addAttribute("aboutText",
                repository.findById("home.about.text").map(Content::getAllContent).orElseThrow());
        return "home";
    }


}
