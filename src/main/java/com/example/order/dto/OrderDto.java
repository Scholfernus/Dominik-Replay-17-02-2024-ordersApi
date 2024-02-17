package com.example.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private String orderNumber;
    private String status;
    private String item;
}
