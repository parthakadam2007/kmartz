package com.example.kmartz.services.discount;

import com.example.kmartz.models.Discount;
import com.example.kmartz.repository.DiscountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DiscountCacheService {

    private final DiscountRepository discountRepository;

    // Cache with code as key (always uppercase for consistency)
    private final Map<String, Discount> discountCache = new ConcurrentHashMap<>();

    public DiscountCacheService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    //Load all discounts into cache on startup
    @PostConstruct
    public void loadCache() {
        discountRepository.findAll().forEach(discount ->
            discountCache.put(discount.getCode().toUpperCase(), discount)
        );
        System.out.println("✅ Loaded " + discountCache.size() + " discounts into cache");
    }

    //  Get discount by code (case-insensitive)
    public Discount getDiscountByCode(String code) {
        if (code == null) return null;
        return discountCache.get(code.toUpperCase());
    }

    //  Update or insert a discount in cache
    public void updateCache(Discount discount) {
        if (discount != null && discount.getCode() != null) {
            discountCache.put(discount.getCode().toUpperCase(), discount);
        }
    }

    // Remove entry when deleted
    public void removeFromCache(String code) {
        if (code != null) {
            discountCache.remove(code.toUpperCase());
        }
    }

    // Force reload all data from DB
    public void reloadAll() {
        discountCache.clear();
        loadCache();
    }

    //  Get all cached discounts
    public Map<String, Discount> getAllCachedDiscounts() {
        return discountCache;
    }

    //  Optional: find by ID (slow scan, but rarely used)
    public Discount getDiscountById(Long id) {
        return discountCache.values().stream()
                .filter(d -> d.getDiscountId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
