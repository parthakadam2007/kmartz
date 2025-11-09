package com.example.kmartz.controller;

import com.example.kmartz.repository.ProductRepository;
import com.example.kmartz.dto.OrderProductDTO;
import com.example.kmartz.dto.ProductDTO;
import com.example.kmartz.models.Product;
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

    @Autowired
    private ProductRepository productRepository;

    // Create new product
    @PostMapping("/create/{shopkeeperId}")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO, @PathVariable Long shopkeeperId) {
        System.out.println("Creating product for shopkeeper ID: " + shopkeeperId);
        productDTO.setShopkeeperId(shopkeeperId);
        ProductDTO createdProduct = productService.createProduct(productDTO);
        System.out.println("Created product: " + createdProduct);
        return ResponseEntity.ok(createdProduct);
    }
    @PostMapping("/order/create/{productId}/{customerId}")
    public ResponseEntity<String> createOrder(
            @PathVariable Long productId,
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "CONFIRMED") String status) {

        productRepository.createOrder(productId, customerId, status);
        return ResponseEntity.ok("Order created successfully");
    }



    //  Get product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        ProductDTO product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    //  Get all products
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Get all products for a shopkeeper
//     @GetMapping("/orders/shopkeeper/{shopkeeperId}")
//     public ResponseEntity<List<Product>> getProductsByShopkeeperId(@PathVariable Long shopkeeperId) {

// List<Product> products = productRepository.findOrdersByShopkeeperId(shopkeeperId);
//         return ResponseEntity.ok(products);
//     }
    // get all orders for a shopkeeper
    @GetMapping("/shopkeeper/{shopkeeperId}/orders")
    public ResponseEntity<List<Object[]>> getShopkeeperOrders(@PathVariable Long shopkeeperId) {
        List<Object[]> orders = productRepository.findOrderProductData(shopkeeperId);
        return ResponseEntity.ok(orders);
    }

    // get all products for a shopkeeper
    @GetMapping("/shopkeeper/{shopkeeperId}")
    public ResponseEntity<List<ProductDTO>> getProductsByShopkeeperIdController(@PathVariable Long shopkeeperId) {
        List<ProductDTO> products = productService.getProductsByShopkeeperId(shopkeeperId);
        return ResponseEntity.ok(products);
    }
}