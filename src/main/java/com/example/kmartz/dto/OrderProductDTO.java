package com.example.kmartz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDTO {
    private Long orderId;
    private int quantity;
    private String status;
    private String productName;
    private Double price;
    private String imageUrl;
    private String description;
    private int rating;
}
