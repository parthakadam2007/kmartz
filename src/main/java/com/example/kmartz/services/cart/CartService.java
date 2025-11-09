package com.example.kmartz.services.cart;

import com.example.kmartz.models.Cart;
import java.util.List;

public interface CartService {
    List<Cart> getCartItems(Long customerId);
    String addToCart(Long customerId, Long productId);
    String removeFromCart(Long customerId, Long productId);
    String incrementQuantity(Long customerId, Long productId);
    String decrementQuantity(Long customerId, Long productId);
}
