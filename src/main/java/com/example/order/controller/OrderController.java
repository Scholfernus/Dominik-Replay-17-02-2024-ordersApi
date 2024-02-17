package com.example.order.controller;

import com.example.order.dto.OrderDto;
import com.example.order.model.OrderModel;
import com.example.order.service.OrderService;
import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping
    public ResponseEntity<List<OrderModel>> getAllOrders() {
        try {
            List<OrderModel> orders = orderService.getAllOrders();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            // Loguj wyjątek dla lepszego zrozumienia problemu
            logger.error("Wystąpił błąd podczas pobierania wszystkich zamówień", e);
            // Zwróć bardziej szczegółową informację o błędzie
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable("id") Long id) {
        try {
            OrderModel model = orderService.getOrderById(id);
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/number/{orderNumber}")
    public ResponseEntity<List<OrderModel>> getOrderByPrice(@Param("orderNumber") String orderNumber) {
        try {
            List<OrderModel> orderModel = orderService.getOrderByPrice(orderNumber);
            return ResponseEntity.ok(orderModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long id,
                                                      @RequestBody OrderDto orderDto) {
        try {
            OrderDto status = orderService.updateOrderStatus(id, orderDto);
            return ResponseEntity.ok(status);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderModel> deleteById(@PathVariable Long id){
        try {
            orderService.deleteOrderById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
