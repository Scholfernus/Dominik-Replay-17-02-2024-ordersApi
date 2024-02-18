package com.example.order.utils;

import com.example.order.dto.OrderDto;
import com.example.order.model.OrderModel;


public class OrderMapper {
    public static OrderModel orderModel(OrderDto orderDto){
        OrderModel orderModel = new OrderModel();
        orderModel.setOrderNumber(orderDto.getOrderNumber());
        orderModel.setItem(orderDto.getItem());
        orderModel.setStatus(orderDto.getStatus());
        return orderModel;
    }
    public static OrderDto orderDto(OrderModel orderModel){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderNumber(orderModel.getOrderNumber());
        orderDto.setItem(orderModel.getItem());
        orderDto.setStatus(orderModel.getStatus());
        return orderDto;
    }
}
