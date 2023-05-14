package com.handelsbanken.ecommerce.repository;

import com.handelsbanken.ecommerce.domain.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Long> {

}
