package com.example.order.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;

@Getter
@Setter
public class OrderDto {
    private String orderNumber;
    private String status;
    private String item;
}
