package com.handelsbanken.ecommerce;

import com.handelsbanken.ecommerce.domain.Discount;
import com.handelsbanken.ecommerce.domain.Watch;
import com.handelsbanken.ecommerce.repository.DiscountRepository;
import com.handelsbanken.ecommerce.repository.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    DiscountRepository discountRepository;
    @Autowired
    WatchRepository watchRepository;

    @Override
    public void run(String... args) throws Exception {
        Watch rolex = new Watch(1L, "Rolex", 100);
        Watch kors = new Watch(2L, "Michael Kors", 80);
        Watch swatch = new Watch(3L, "Swatch", 50);
        Watch casio = new Watch(4L, "Casio", 30);

        Discount rolexDiscount = new Discount( rolex, 3, 200);
        Discount korsDiscount = new Discount(kors, 2, 120);

        rolex.setDiscount(rolexDiscount);
        kors.setDiscount(korsDiscount);

        watchRepository.saveAll(List.of(rolex, kors, swatch, casio));
        discountRepository.saveAll(List.of(rolexDiscount, korsDiscount));
    }
}
