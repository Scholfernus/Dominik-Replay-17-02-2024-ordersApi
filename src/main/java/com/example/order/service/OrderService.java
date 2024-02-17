package com.example.order.service;

import com.example.order.dto.OrderDto;
import com.example.order.model.OrderModel;
import com.example.order.repository.OrderRepository;
import com.example.order.utils.OrderMapper;
import com.example.order.utils.error.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);


    public List<OrderModel> getAllOrders() {
        List<OrderModel> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            logger.warn("Brak danych w tabeli zamówień");
        }
        return orders;
    }

    public OrderModel getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<OrderModel> getOrderByPrice(String orderNumber) {
        return orderRepository.findByStatus(orderNumber);
    }

    public OrderDto updateOrderStatus(Long id, OrderDto orderDto) {
        OrderModel orderModel = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order doesn't exist"));
                orderModel.setStatus(orderDto.getStatus());
        OrderModel savedOrder = orderRepository.save(orderModel);
        return OrderMapper.orderDto(savedOrder);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
