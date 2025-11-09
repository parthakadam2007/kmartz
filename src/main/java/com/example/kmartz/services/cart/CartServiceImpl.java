package com.example.kmartz.services.cart;

import com.example.kmartz.models.Cart;
import com.example.kmartz.models.Customer;
import com.example.kmartz.models.Product;
import com.example.kmartz.repository.CartRepository;
import com.example.kmartz.repository.CustomerRepository;
import com.example.kmartz.repository.ProductRepository;
import com.example.kmartz.services.cart.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired private CartRepository cartRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private CustomerRepository customerRepository;

    @Override
    public List<Cart> getCartItems(Long customerId) {
        return cartRepository.findByCustomerId(customerId);
    }

    @Override
    public String addToCart(Long customerId, Long productId) {
        Cart existing = cartRepository.findByCustomerIdAndProductId(customerId, productId);
        if (existing != null) {
            cartRepository.incrementQuantity(customerId, productId);
            return "Product quantity increased.";
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Cart newCart = Cart.builder()
                .customer(customer)
                .product(product)
                .quantity(1)
                .build();
        cartRepository.save(newCart);
        return "Product added to cart.";
    }

    @Override
    public String removeFromCart(Long customerId, Long productId) {
        cartRepository.deleteProductFromCart(customerId, productId);
        return "Product removed from cart.";
    }

    @Override
    public String incrementQuantity(Long customerId, Long productId) {
        cartRepository.incrementQuantity(customerId, productId);
        return "Quantity increased.";
    }

    @Override
    public String decrementQuantity(Long customerId, Long productId) {
        cartRepository.decrementQuantity(customerId, productId);
        return "Quantity decreased.";
    }
}
