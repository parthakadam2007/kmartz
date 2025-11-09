package com.example.kmartz.services.discount;
import org.springframework.stereotype.Service;

import com.example.kmartz.models.Discount;
import com.example.kmartz.repository.DiscountRepository;
@Service
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final DiscountCacheService discountCacheService;

    public DiscountService(DiscountRepository discountRepository, DiscountCacheService discountCacheService) {
        this.discountRepository = discountRepository;
        this.discountCacheService = discountCacheService;
    }

    public Discount createDiscount(Discount discount) {
        Discount saved = discountRepository.save(discount);
        discountCacheService.updateCache(saved);
        return saved;
    }

    public void deleteDiscount(String code) {
        discountRepository.deleteByCode(code);
        discountCacheService.removeFromCache(code);
    }
}
