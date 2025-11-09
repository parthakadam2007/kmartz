package com.example.kmartz.repository;

import com.example.kmartz.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Discount findByCode(String code);

    @Modifying
    @Transactional
    @Query("DELETE FROM Discount d WHERE d.code = :code")
    void deleteByCode(@Param("code") String code);
}
