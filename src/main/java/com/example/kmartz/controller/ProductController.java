package com.example.kmartz.controller;

import com.example.kmartz.dto.ProductDTO;
import com.example.kmartz.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ✅ Create new product
    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.ok(createdProduct);
    }

    // ✅ Get product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        ProductDTO product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    // ✅ Get all products
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // ✅ Get all products for a shopkeeper
    @GetMapping("/shopkeeper/{shopkeeperId}")
    public ResponseEntity<List<ProductDTO>> getProductsByShopkeeperId(@PathVariable Long shopkeeperId) {
        List<ProductDTO> products = productService.getProductsByShopkeeperId(shopkeeperId);
        return ResponseEntity.ok(products);
    }
}