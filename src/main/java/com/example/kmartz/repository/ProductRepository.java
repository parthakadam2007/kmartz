package com.example.kmartz.repository;

import com.example.kmartz.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.repository.query.Param;

import java.util.List;
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 1️⃣ Get all products by shopkeeper ID
    @Query(value = "SELECT * FROM products WHERE shopkeeper_id = :shopkeeperId", nativeQuery = true)
    List<Product> findByShopkeeperId(@Param("shopkeeperId") Long shopkeeperId);

    // 2️⃣ Get product by name (exact match)
    @Query(value = "SELECT * FROM products WHERE name = :name", nativeQuery = true)
    Product findByName(@Param("name") String name);

    // 3️⃣ Search products by partial name match (case-insensitive)
    @Query(value = "SELECT * FROM products WHERE LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Product> searchByName(@Param("keyword") String keyword);

    // 4️⃣ Get all products within a price range
    @Query(value = "SELECT * FROM products WHERE price BETWEEN :minPrice AND :maxPrice", nativeQuery = true)
    List<Product> findByPriceRange(@Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice);

    // 5️⃣ Get all products with a minimum rating
    @Query(value = "SELECT * FROM products WHERE rating >= :rating", nativeQuery = true)
    List<Product> findByMinRating(@Param("rating") Integer rating);

    // 6️⃣ Get all products sorted by price (ascending)
    @Query(value = "SELECT * FROM products ORDER BY price ASC", nativeQuery = true)
    List<Product> findAllSortedByPriceAsc();

    // 7️⃣ Get all products sorted by price (descending)
    @Query(value = "SELECT * FROM products ORDER BY price DESC", nativeQuery = true)
    List<Product> findAllSortedByPriceDesc();
    // create order
    @Query(value = "SELECT * FROM products WHERE order_id = :orderId", nativeQuery = true)
    List<Product> createOrder(@Param("orderId") Long orderId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO orders (product_id, customer_id, status) VALUES (:productId, :customerId, :status)", nativeQuery = true)
    void createOrder(@Param("productId") Long productId,
                     @Param("customerId") Long customerId,
                     @Param("status") String status);
}
