package com.example.kmartz.controller;

import com.example.kmartz.models.Product;
import com.example.kmartz.services.product.ProductService;
import com.example.kmartz.services.cache.RecentlyViewedProductsService;
import com.example.kmartz.services.cache.RecentlyViewedProducts;
import com.example.kmartz.services.cache.RecentlyViewedProducts.Node;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/recently-viewed")
public class RecentlyViewedController {

    private final RecentlyViewedProductsService recentlyViewedService;
    private final ProductService productService;

    public RecentlyViewedController(RecentlyViewedProductsService recentlyViewedService,
                                    ProductService productService) {
        this.recentlyViewedService = recentlyViewedService;
        this.productService = productService;
    }

    @PostMapping("/add/{customerId}/{productId}")
    public String addProduct(@PathVariable Long customerId, @PathVariable Long productId) {
        // get DTO from service and map to entity before adding
        com.example.kmartz.dto.ProductDTO productDto = productService.getProductById(productId); // fetch product DTO
        Product product = new Product();
        // map fields from DTO to entity (adjust mappings if Product/DTO fields differ)
        product.setProductId(productDto.getProductId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        recentlyViewedService.addProduct(customerId, product);
        return "Product added to recently viewed list";
    }

    @GetMapping("/{customerId}")
    public List<Product> getRecentlyViewed(@PathVariable Long customerId) {
        RecentlyViewedProducts list = recentlyViewedService.getRecentlyViewed(customerId);
        List<Product> result = new ArrayList<>();
        Node current = list.getHead();
        while (current != null) {
            result.add(current.product);
            current = current.next;
        }
        return result;
    }
}
