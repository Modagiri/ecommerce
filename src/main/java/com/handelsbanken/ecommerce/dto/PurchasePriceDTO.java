package com.handelsbanken.ecommerce.dto;


public class PurchasePriceDTO {
    private Integer price;

    public PurchasePriceDTO(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void addToPrice(Integer price) {
        this.price += price;
    }
}
