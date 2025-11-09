package com.example.kmartz.services.cache;

import com.example.kmartz.models.Product;
import com.example.kmartz.services.cache.RecentlyViewedProducts;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecentlyViewedProductsService {

    public final int MAX_SIZE = 10; // Max recently viewed per user
    public final Map<Long, RecentlyViewedProducts> userCache = new HashMap<>();

    public void addProduct(Long customerId, Product product) {
        userCache.putIfAbsent(customerId, new RecentlyViewedProducts(MAX_SIZE));
        userCache.get(customerId).addProduct(product);
    }

    public RecentlyViewedProducts getRecentlyViewed(Long customerId) {
        return userCache.getOrDefault(customerId, new RecentlyViewedProducts(MAX_SIZE));
    }
}
