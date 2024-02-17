package com.example.order.repository;

import com.example.order.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
@Query("SELECT p from OrderModel p WHERE p.orderNumber = :orderNumber")
    List<OrderModel> findByStatus(String orderNumber);
}
