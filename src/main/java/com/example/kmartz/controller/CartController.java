package com.example.kmartz.controller;

import com.example.kmartz.models.Cart;
import com.example.kmartz.services.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {

    @Autowired private CartService cartService;

    @GetMapping("/{customerId}")
    public List<Cart> getCartItems(@PathVariable Long customerId) {
        return cartService.getCartItems(customerId);
    }

    @PostMapping("/add/{customerId}/{productId}")
    public String addToCart(@PathVariable Long customerId, @PathVariable Long productId) {
        return cartService.addToCart(customerId, productId);
    }
//     @PostMapping("/add")
// public ResponseEntity<String> addToCart(@RequestBody CartRequest request) {
//     cartService.addToCart(request.getCustomerId(), request.getProductId(), request.getQuantity());
//     return ResponseEntity.ok("Product added to cart successfully");
// }

    @DeleteMapping("/remove/{customerId}/{productId}")
    public String removeFromCart(@PathVariable Long customerId, @PathVariable Long productId) {
        return cartService.removeFromCart(customerId, productId);
    }

    @PutMapping("/increment/{customerId}/{productId}")
    public String incrementQuantity(@PathVariable Long customerId, @PathVariable Long productId) {
        return cartService.incrementQuantity(customerId, productId);
    }

    @PutMapping("/decrement/{customerId}/{productId}")
    public String decrementQuantity(@PathVariable Long customerId, @PathVariable Long productId) {
        return cartService.decrementQuantity(customerId, productId);
    }
}
