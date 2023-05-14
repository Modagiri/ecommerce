package com.handelsbanken.ecommerce.controller;

import com.handelsbanken.ecommerce.domain.Watch;
import com.handelsbanken.ecommerce.dto.PurchasePriceDTO;
import com.handelsbanken.ecommerce.dto.WatchInDTO;
import com.handelsbanken.ecommerce.service.CheckoutService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;

    @PostMapping("/checkout")
    public ResponseEntity<PurchasePriceDTO> checkoutItems(@RequestBody List<WatchInDTO> watches) {
        PurchasePriceDTO totalPrice = checkoutService.getTotalPriceAtCheckout(watches);

        return ResponseEntity.ok(totalPrice);
    }
}
