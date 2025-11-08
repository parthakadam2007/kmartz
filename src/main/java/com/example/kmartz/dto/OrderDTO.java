package com.example.kmartz.dto;

import com.example.kmartz.models.OrderStatus;
import com.example.kmartz.models.OrderStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {
    private Long orderId;
    private Long productId;
    private Long customerId;
    private OrderStatus orderStatus;
}
