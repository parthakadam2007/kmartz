package com.example.kmartz.repository;

import com.example.kmartz.models.Cart;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Get all cart items for one customer
    @Query("SELECT c FROM Cart c WHERE c.customer.customerId = :customerId")
    List<Cart> findByCustomerId(@Param("customerId") Long customerId);

    // Check if product already exists in customer's cart
    @Query("SELECT c FROM Cart c WHERE c.customer.customerId = :customerId AND c.product.productId = :productId")
    Cart findByCustomerIdAndProductId(@Param("customerId") Long customerId, @Param("productId") Long productId);

    // Increment quantity
    @Modifying
    @Query("UPDATE Cart c SET c.quantity = c.quantity + 1 WHERE c.customer.customerId = :customerId AND c.product.productId = :productId")
    void incrementQuantity(@Param("customerId") Long customerId, @Param("productId") Long productId);

    // Decrement quantity (won’t go below 1)
    @Modifying
    @Query("UPDATE Cart c SET c.quantity = CASE WHEN c.quantity > 1 THEN c.quantity - 1 ELSE 1 END WHERE c.customer.customerId = :customerId AND c.product.productId = :productId")
    void decrementQuantity(@Param("customerId") Long customerId, @Param("productId") Long productId);

    // Delete product from cart
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.customer.customerId = :customerId AND c.product.productId = :productId")
    void deleteProductFromCart(@Param("customerId") Long customerId, @Param("productId") Long productId);
}
