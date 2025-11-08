package com.example.kmartz.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
    private Long productId;
    private Long shopkeeperId;
    private String imageUrl;
    private String name;
    private String description;
    private Integer rating;
    private Integer price;
}
