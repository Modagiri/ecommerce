package com.handelsbanken.ecommerce.service;

import com.handelsbanken.ecommerce.domain.Discount;
import com.handelsbanken.ecommerce.domain.Watch;
import com.handelsbanken.ecommerce.dto.PurchasePriceDTO;
import com.handelsbanken.ecommerce.dto.WatchInDTO;
import com.handelsbanken.ecommerce.repository.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheckoutService {

    @Autowired
    WatchRepository watchRepository;

    public PurchasePriceDTO getTotalPriceAtCheckout(List<WatchInDTO> watches) {
        Map<Integer, Long> orderMap = watches.stream().collect(Collectors.groupingBy(watch -> watch.getId(), Collectors.counting()));
        PurchasePriceDTO totalPrice = new PurchasePriceDTO(0);
        orderMap.forEach(
                (key, value) -> totalPrice.addToPrice(
                        getTotalCostForWatch(key.longValue(), value.intValue())));
        return totalPrice;
    }

    public Integer getTotalCostForWatch(Long watchId, Integer numberOfWatches) {
        Watch watch = watchRepository.getReferenceById(watchId);
        Discount discount = watch.getDiscount();

        if(!qualifiesForDiscount(discount, numberOfWatches)) {
            return watch.getUnitPrice() * numberOfWatches;
        }

        return getDiscountedPrice(watch, numberOfWatches);

    }

    private Integer getDiscountedPrice(Watch watch, Integer numberOfWatches) {
        int count = numberOfWatches;
        int minimumCountForDiscount = watch.getDiscount().getItems();
        int price = 0;

         while(count >= minimumCountForDiscount) {
             price += watch.getDiscount().getDiscountPrice();
             count -= watch.getDiscount().getItems();
        }

        price += (count * watch.getUnitPrice());

        return price;
    }

    private boolean qualifiesForDiscount(Discount discount, Integer numberOfItems) {
        if(discount == null) {
            return false;
        }

        return discount.getItems() <= numberOfItems;
    }

}
