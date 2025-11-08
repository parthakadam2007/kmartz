package com.example.kmartz.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "shopkeeperId", nullable = false)
    private Long shopkeeperId;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Integer rating;

    @Column(nullable = false)
    private Integer price;
}