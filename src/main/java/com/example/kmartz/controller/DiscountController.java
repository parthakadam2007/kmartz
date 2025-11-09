package com.example.kmartz.controller;

import com.example.kmartz.models.Discount;
import com.example.kmartz.services.discount.DiscountCacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountCacheService discountCacheService;

    public DiscountController(DiscountCacheService discountCacheService) {
        this.discountCacheService = discountCacheService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllDiscounts() {
        return ResponseEntity.ok(discountCacheService.getAllCachedDiscounts());
    }

    // ✅ Get discount by code
    @GetMapping("/{code}")
    public ResponseEntity<?> getDiscountByCode(@PathVariable String code) {
        Discount discount = discountCacheService.getDiscountByCode(code);

        if (discount == null) {
            return ResponseEntity
                    .status(404)
                    .body("Discount not found for code: " + code);
        }

        return ResponseEntity.ok(discount);
    }
}
