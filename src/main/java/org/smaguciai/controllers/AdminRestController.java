package org.smaguciai.controllers;

import org.smaguciai.entities.Order;
import org.smaguciai.enumerators.OrderStatus;
import org.smaguciai.enumerators.Performer;
import org.smaguciai.events.HomeImageDto;
import org.smaguciai.services.ImageService;
import org.smaguciai.services.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class AdminRestController {
    @Value("${file.upload-dir}")
    private String uploadDir;
    private final OrderService service;
    private final ImageService imageService;


    public AdminRestController(OrderService service, ImageService imageService) {
        this.service = service;
        this.imageService=imageService;
    }

    @PostMapping("/api/admin/orders/{id}/approved/{performer}")
    public ResponseEntity<Order> approveOrder(@PathVariable Long id, @PathVariable Performer performer){
        return ResponseEntity.ok(service.updateStatusAndPerfomer(id, OrderStatus.PRIIMTAS, performer));

    }
    @PostMapping("/api/admin/orders/{id}/rejected")
    public ResponseEntity<Order> rejectedOrder(@PathVariable Long id){
        return ResponseEntity.ok(service.rejectOrder(id, OrderStatus.ATMESTA));
    }
    @GetMapping("/admin/orders/pending")
        public List<Order> getPendingOrders(){
            return service.getOrdersByStatus(OrderStatus.LAUKIAMAS);
        }

    @GetMapping("/admin/orders/")
    public List<Order> getApprovedOrders(){
        return service.getOrdersByStatus(OrderStatus.PRIIMTAS);
    }
    @PutMapping("/api/admin/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updated){
        return ResponseEntity.ok(service.updateOrder(id, updated));
    }
    @DeleteMapping("/api/admin/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
    @ResponseBody
    @PostMapping("/admin/content/updateImage")
    public ResponseEntity<Void> updateImage(@RequestParam MultipartFile file,
                              @RequestParam String section,
                              @RequestParam int position)throws IOException {
       imageService.saveOrUpdate(section, position, file);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/admin/content/getImage")
    @ResponseBody
    public ResponseEntity<HomeImageDto> getImage (@RequestParam String section, @RequestParam int position){
        return imageService.getBySectionAndPosition(section, position)
                .map(image -> new HomeImageDto(
                        image.getSection(),
                        image.getFileName(),
                        image.getPosition()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
    @PostMapping("admin/content/deleteImage")
    public void deleteImage(@RequestBody HomeImageDto dto){
        imageService.delete(dto.section(), dto.position());
    }
}

