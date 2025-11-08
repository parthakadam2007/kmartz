package com.example.kmartz.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "discounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer discountId;

    @Column(length = 10, nullable = false)
    private String code;

    @Column(nullable = false)
    private Integer discount;

    @Column(nullable = false)
    private String operator; // allowed values: "off", "percentage"

    private Integer minAmount;
}