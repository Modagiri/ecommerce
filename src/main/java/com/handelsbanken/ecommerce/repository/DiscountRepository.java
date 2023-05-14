package com.handelsbanken.ecommerce.repository;

import com.handelsbanken.ecommerce.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Optional<Discount> getDiscountByWatchId(Long watchId);
}
