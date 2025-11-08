package com.example.kmartz.dto;

import com.example.kmartz.models.DiscountOperator;
import com.example.kmartz.models.DiscountOperator;  

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiscountDTO {
    private Long discountId;
    private String code;
    private Integer discount;
    private DiscountOperator operator;
    private Integer minAmount;
}
